import java.util.List;
import java.util.stream.Collectors;

public class ListingManipulator
{
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
}