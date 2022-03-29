import java.util.stream.Collectors;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.Arrays;

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
    public static List<AirbnbListing> filterByBorough
    (List<AirbnbListing> listings, String boroughName)
    {
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
    public static List<AirbnbListing> filterByPriceRange
    (List<AirbnbListing> listings, int fromPrice, int toPrice)
    {
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
    public static int getNoOfPropertiesInBoroughWithMost
    (List<AirbnbListing> listings)
    {
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
    public static int getMaxPropertyPrice()
    {
        return listings.stream()
                .map(AirbnbListing::getPrice)
                .max(Integer::compare)
                .orElse(0);
    }
    
    /**
     * This finds the AirbnbListing with the id given.
     * @param id The id for the listing you you want to find.
     * @return The AirbnbListing with the id given, null if non are found.
     */
    public static AirbnbListing getListingWithId(String id)
    {
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
     * Augusto
     */
    public static List<DestinationListing> filterDestinations(List<DestinationListing> destinations, String borough, String price)
    {
        return destinations.stream()
                           .filter(destination -> validBorough(borough) && borough != null && borough.equals(destination.getBorough()))
                           .filter(destination -> price != null && price.equals(destination.getPrice()))
                           .collect(Collectors.toList());
        
    }
    
    /**
     * Augusto
     */
    public static AirbnbListing getPropertyListingByNames(List<AirbnbListing> listings, String listingName, String boroughName)
    {
       AirbnbListing selectedProperty = listings.stream()
                                                .filter(listing -> listingName.equals(listing.getName()) && boroughName.equals(listing.getNeighbourhood()))
                                                .findFirst().orElse(null); 
       //perform try catch to possibly simulate better behaviour in case of null thrown                                         
       return selectedProperty;
    }
    
    //The below streams are used in the pie chart functionalities
    /**
     * Adam
     */
    public static int[] getListingPrices(List<AirbnbListing> listings)
    {
        return listings.stream()
                .mapToInt(listing -> listing.getPrice())
                .toArray();
    }
    
    /**
     * Adam
     */
    public static int[] getListingReviews(List<AirbnbListing> listings)
    {
        return listings.stream()
                .mapToInt(listing -> listing.getNumberOfReviews())
                .toArray();
    }
    
    /**
     * Adam
     */
    public static int[] getListingMinNights(List<AirbnbListing> listings)
    {
        return listings.stream()
                       .mapToInt(listing -> listing.getMinimumNights())
                       .toArray();
    }
    
    /**
     * Augusto
     */
    public static int getMin(int[] values)
    {
        return Arrays.stream(values)
                         .boxed()
                         .min(Integer::compare)
                         .get();
    }
    
    /**
     * Augusto
     */
    public static int getMax(int[] values)
    {
        return Arrays.stream(values)
                     .boxed()
                     .max(Integer::compare)
                     .get();  
    }
    
    /**
     * Augusto
     */
    public static long retrieveSpeciedAmount(int[] values , int from, int to)
    {
        return Arrays.stream(values)
                      .filter(i -> (i >= from) && (i < to))
                      .count();
    }
    
    /**
     * @param listings The listings to be sorted through.
     * @return A Position object containing the average latitude and longitude of the listings.
     */
    public static Position getAveragePosition(List<AirbnbListing> listings)
    {
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
        listings = filterByPriceRange(listings,fromPrice,toPrice);
        return listings.stream()
            .mapToInt(listing -> listing.getNumberOfReviews())
            .sum();
    }
    
    /**
     * Get the total number of listings for all properties within the price range
     */
    public static long getNumberofListings(List<AirbnbListing> listings, int fromPrice, int toPrice) {
        listings = filterByPriceRange(listings, fromPrice, toPrice);
        return listings.stream()
            .count();
    }
    
    
    /**
     * Get the total number of availability for all properties within the price range
     */
    public static long getTotalAvailability(List<AirbnbListing> listings, int fromPrice, int toPrice) {
        listings = filterByPriceRange(listings, fromPrice, toPrice);
        return listings.stream()
            .filter(listing -> listing.getAvailability365() > 0) 
            .count();
    }    
    
    /**
     *  Get the total number of non private rooms for all properties within the price range
     */
    public static long getNonPrivate(List<AirbnbListing> listings, String roomNeeded, int fromPrice, int toPrice) {
        listings = filterByPriceRange(listings, fromPrice, toPrice);
        return listings.stream()
            .filter(listing -> listing.getRoom_type().equals(roomNeeded)) 
            .count();
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