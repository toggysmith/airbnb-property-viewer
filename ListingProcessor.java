import java.util.stream.Collectors;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.lang.NullPointerException;

/**
 * Responsible for providing methods to usefully manipulate individual Airbnb listings and lists of listings.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class ListingProcessor
{
    private static List<AirbnbListing> listings = AirbnbDataLoader.getListings();
    
    /**
     * Filter listings to only include those in a specific borough.
     * @param listings The listings to filter.
     * @param boroughName The name of the borough (neighbourhood) to check.
     * @return The listings in a specific borough.
     */
    public static List<AirbnbListing> filterByBorough(List<AirbnbListing> listings, String boroughName)
    {
        checkValidAirbnbListings(listings);
        checkValidBoroughName(boroughName);
        
        return listings.stream()
                       .filter(listing -> boroughName.equals(listing.getNeighbourhood()))
                       .collect(Collectors.toList());
    }
    
    /**
     * Filter listings to only include those in a specific price range.
     * @param listings The listings to filter.
     * @param fromPrice The price to filter from.
     * @param toPrice The price to filter up to.
     * @return The filtered listings.
     */
    public static List<AirbnbListing> filterByPriceRange(List<AirbnbListing> listings, int fromPrice, int toPrice)
    {
        checkValidAirbnbListings(listings);
        checkValidPriceRange(fromPrice, toPrice);
        
        return listings.stream()
                       .filter(listing -> listing.getPrice() >= fromPrice
                                          && listing.getPrice() <= toPrice)
                       .collect(Collectors.toList());
    }
    
    /**
     * Get the number of properties in the borough with the most properties.
     * @param listings The listings to be used to check.
     * @return The number of properties.
     */
    public static int getNoOfPropertiesInBoroughWithMost(List<AirbnbListing> listings)
    {
        checkValidAirbnbListings(listings);
        
        int most = 0;
        
        for (Borough borough : Borough.values())
        {
            List<AirbnbListing> listingsInBorough = filterByBorough(listings, borough.getName());
            
            if (listingsInBorough.size() > most)
            {
                most = listingsInBorough.size();
            }
        }
        
        return most;
    }
    
    /**
     * @return The maximum property price or zero if there is none.
     */
    public static int getMaxPropertyPrice(List<AirbnbListing> listings)
    {
        checkValidAirbnbListings(listings);
        
        return listings.stream()
                .map(AirbnbListing::getPrice)
                .max(Integer::compare)
                .get();
    }
    
    /**
     * This finds the AirbnbListing with the id given.
     * @param id The id for the listing you you want to find. Cannot be null.
     * @return The AirbnbListing with the id given, null if none are found.
     */
    public static AirbnbListing getListingWithId(List<AirbnbListing> listings, String id)
    {
        checkValidAirbnbListings(listings);
        
        if (id == null)
        {
            throw new IllegalArgumentException("The id argument cannot be null.");
        }
        
        try
        {
            return listings.stream()
                .filter(listing -> listing.getId().equals(id))
                .findFirst()
                .get();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     * This gets a list of all the AirbnbListing's by the host that also owns the paramater hostListing, not including the hostListing.
     * @param hostListing The listing by the host you want to find other listings for.
     * @return The other AirbnbListing's by the host given.
     */
    public static List<AirbnbListing> getOtherListingsWithHostId(AirbnbListing hostListing)
    {
        try
        {
            return listings.stream()
                .filter(listing -> (listing.getHost_id().equals(hostListing.getHost_id())) && !(listing.getId().equals(hostListing.getId())))
                .collect(Collectors.toList());
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     *This method returns all the distinct borough names as a list of strings relative to a list of AirbnbListings, by mapping to a new stream, the listings' neighbourhood attribute (in which borough the property exists)
     *@param List<AirbnbListing> listing, the list of Airbnb properties to parse
     *@return List<String>, a string containing all the unique borough names present in the list of AirbnbListing properties
     */
    public static List<String> getBoroughs(List<AirbnbListing> listing) 
    {
        return listing.stream()
                      .map(l -> l.getNeighbourhood())
                      .filter(borough -> borough != null && validBorough(borough))
                      .distinct()
                      .collect(Collectors.toList());
    }
    
    /**
     * This method returns a list of strings containing the names of the AirbnbListing listings that have the same borough name as the boroughName passed in the method. 
     * @param (List<AirbnbListing> listings, list of properties
     * @param boroughName, borough name on which the properties will be filtered on
     * @return List<String>, all properties from the passed listings that have the same "boroughName" attribute
     */
    public static List<String> getPropertiesNameInBorough(List<AirbnbListing> listings, String boroughName)
    {
        return listings.stream()
                       .filter(listing -> boroughName != null && validBorough(boroughName) && boroughName.equals(listing.getNeighbourhood()))
                       .map(listing -> listing.getName())
                       .distinct()
                       .collect(Collectors.toList());
    }
    
    /**
     * This method filters the destinations either pubs or attractions by the borough entered and the price entered, the borough must be a valid one (from enumerated Borough class) and the prices must be valid for either the 
     * DestinationListing PUB (£,££,£££) or DestinationListing attraction(free,£2.50 - £5.00, £5.00 - £7.00 ,£7.00 - £9.00)
     * 
     * Providing an invalid price in method args will throw IllegalArgumentException
     * 
     * @param List<DestinationListing> destinations, list of destinations either pubs or attractions
     * @param String borough, the name of the borough
     * @param price, a valid price for either the pubs or attractions to filter on
     * @return List<Destination>, list of destinations that satisfy the filtered requirements
     */
    public static List<DestinationListing> filterDestinations(List<DestinationListing> destinations, String borough, String price)
    {
        //The list of destinations could either be a list of pubs or attractions thus the double check for prices
        PubPrice pubPricings = new PubPrice();
        AttractionPrice attractionPricings = new AttractionPrice();        
        if(!pubPricings.checkPriceValid(price) && !attractionPricings.checkPriceValid(price)){
            throw new IllegalArgumentException("Invalid Price Passed");
        }else{
        return destinations.stream()
                           .filter(destination -> validBorough(borough) && borough != null && borough.equals(destination.getBorough()))
                           .filter(destination -> (pubPricings.checkPriceValid(destination.getPrice()) || attractionPricings.checkPriceValid(destination.getPrice())))
                           .filter(destination -> price.equals(destination.getPrice()))
                           .collect(Collectors.toList());
        }
    }
    
    /**
     * Returns a single property whose name and borough match the attributes passed by the method header
     * 
     */
    public static AirbnbListing getPropertyListingByNames(List<AirbnbListing> listings, String listingName, String boroughName)
    {
        if(listingName == null){
            throw new IllegalArgumentException("Invalid listing name provided");
        }
        if(boroughName == null){
            throw new IllegalArgumentException("Invalid borough name provided");
        }
        
       AirbnbListing selectedProperty = listings.stream()
                                                .filter(listing -> listingName.equals(listing.getName()) && boroughName.equals(listing.getNeighbourhood()))
                                                .findFirst().orElse(null); 
       return selectedProperty;
    }
    
    //The below streams are used in the pie chart functionalities
    /**
     * The turns all the AirbnbListings to an array of their prices.
     * @param listings The listings that you want the price for.
     * @return The prices for the listings as an array of int.
     */
    public static int[] getListingPrices(List<AirbnbListing> listings)
    {
        if (listings == null)
        {
            throw new IllegalArgumentException("The provided listings argument is invalid.");
        }
        return listings.stream()
                .mapToInt(listing -> listing.getPrice())
                .toArray();
    }
    
    /**
     * The turns all the AirbnbListings to an array of their number of reviews.
     * @param listings The listings that you want the number of reviews for.
     * @return The number of reviews for the listings as an array of int.
     */
    public static int[] getListingReviews(List<AirbnbListing> listings)
    {
        if (listings == null)
        {
            throw new IllegalArgumentException("The provided listings argument is invalid.");
        }
        return listings.stream()
                .mapToInt(listing -> listing.getNumberOfReviews())
                .toArray();
    }
    
    /**
     * The turns all the AirbnbListings to an array of their min nights.
     * @param listings The listings that you want the min nights for.
     * @return The min nights for the listings as an array of int.
     */
    public static int[] getListingMinNights(List<AirbnbListing> listings)
    {
        if (listings == null)
        {
            throw new IllegalArgumentException("The provided listings argument is invalid.");
        }
        return listings.stream()
                       .mapToInt(listing -> listing.getMinimumNights())
                       .toArray();
    }
    
    /**
     * Gets the minimum value from the integer array passed to this method
     * @param, int[] values, integer array
     * @return int, the minimum integer from the array passed
     */
    public static int getMin(int[] values)
    {
        if(values.length == 0 || values == null){
            throw new IllegalArgumentException("empty array passed");
        }else{
        return Arrays.stream(values)
                         .boxed()
                         .min(Integer::compare)
                         .get();
        }
    }
    
    /**
     * Gets the maximum value from the integer array passed to this method
     * @param, int[] values, integer array
     * @return int, the maximum integer from the array passed
     */
    public static int getMax(int[] values)
    {
        if(values.length == 0 || values == null){
            throw new IllegalArgumentException("empty array passed");
        }else{
        return Arrays.stream(values)
                     .boxed()
                     .max(Integer::compare)
                     .get();  
        }
    }
    
    /**
     * Returns a specific amount of elements within the specified range
     */
    public static long retrieveSpecifiedAmount(int[] values , int from, int to)
    {
        if(values.length == 0 || values == null){
            throw new IllegalArgumentException("empty array passed");
        }else{
        return Arrays.stream(values)
                      .filter(i -> (i >= from) && (i < to))
                      .count();
        }              
    }
    
    /**
     * @param listings The listings to be sorted through.
     * @return A Position object containing the average latitude and longitude of the listings.
     */
    public static Position getAveragePosition(List<AirbnbListing> listings)
    {
        checkValidAirbnbListings(listings);
        try
        {
            return new Position(
                 listings.stream()
                .mapToDouble(listing -> listing.getLatitude())
                .average()
                .getAsDouble(),
                
                listings.stream()
                .mapToDouble(listing -> listing.getLongitude())
                .average()
                .getAsDouble());
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    
    /**
     * Get the total number of reviews for all properties within the price range
     */
    public static int getNumberOfReviews(List<AirbnbListing> listings, int fromPrice, int toPrice) {
        checkValidAirbnbListings(listings);
        
        listings = filterByPriceRange(listings,fromPrice,toPrice);
        return listings.stream()
            .mapToInt(listing -> listing.getNumberOfReviews())
            .sum();
    }
    
    /**
     * Get the total number of listings for all properties within the price range
     */
    public static long getNumberOfListings(List<AirbnbListing> listings, int fromPrice, int toPrice) {
        checkValidAirbnbListings(listings);
        
        listings = filterByPriceRange(listings, fromPrice, toPrice);
        return listings.stream()
            .count();
    }
    
    
    /**
     * Get the total number of availability for all properties within the price range
     */
    public static long getTotalAvailableProperties(List<AirbnbListing> listings, int fromPrice, int toPrice) {
        checkValidAirbnbListings(listings);
        
        listings = filterByPriceRange(listings, fromPrice, toPrice);
        return listings.stream()
            .filter(listing -> listing.getAvailability365() > 0) 
            .count();
    }    
    
    /**
     *  Get the total number of non private rooms for all properties within the price range
     */
    public static long getNonPrivate(List<AirbnbListing> listings, String roomNeeded, int fromPrice, int toPrice) {
        checkValidAirbnbListings(listings);
        
        if(roomNeeded != "Entire home/apt") 
        {
            throw new IllegalArgumentException("The provided room type argument does not exist");
        }
        
        listings = filterByPriceRange(listings, fromPrice, toPrice);
        return listings.stream()
            .filter(listing -> listing.getRoom_type().equals(roomNeeded)) 
            .count();
    }
    
    private static void checkValidAirbnbListings(List<AirbnbListing> listings)
    {
        if (listings == null || listings.isEmpty())
        {
            throw new IllegalArgumentException("The provided listings argument is invalid.");
        }
    }
    
    private static void checkValidBoroughName(String boroughName)
    {
        Borough[] validBoroughs = Borough.values();
        
        for(int i = 0; i <= validBoroughs.length - 1; i++){
            if(validBoroughs[i].getName().equals(boroughName)){
                return;
            }
        }
        
        throw new IllegalArgumentException("The provided borough name argument is invalid.");
    }
    
    private static void checkValidPriceRange(int fromPrice, int toPrice)
    {
        if (fromPrice > toPrice)
        {
            throw new IllegalArgumentException("The from-price argument cannot be greater than the to-price argument.");
        }
    }
    
    /*
     * method to check that the borough passed is valid in relation to the Borough enumeration class
     */
    private static boolean validBorough(String boroughName)
    {
        Borough[] validBoroughs = Borough.values();
        
        for(int i = 0; i <= validBoroughs.length - 1; i++){
            if(validBoroughs[i].getName().equals(boroughName)){
                return true;
            }
        }
        return false;
    }
    
    
}