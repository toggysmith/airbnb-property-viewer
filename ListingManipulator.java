import java.util.stream.Collectors;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Responsible for providing methods to usefully manipulate individual Airbnb listings and lists of listings.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class ListingManipulator
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
     * Augusto
     */
    public static List<String> getBoroughs(List<AirbnbListing> listing)
    {
        return listing.stream()
                      .map(l -> l.getNeighbourhood())
                      .distinct()
                      .collect(Collectors.toList());
    }
}