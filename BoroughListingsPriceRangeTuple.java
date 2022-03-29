import java.util.Objects;
import java.util.List;

/**
 * This stores the Borough, List of AirbnbListings, and PriceRange for a BoroughDetailsStage.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */ 

public class BoroughListingsPriceRangeTuple
{
    private Borough borough;
    private List<AirbnbListing> listings;
    private PriceRange priceRange;

    /**
     * Constructor for BoroughListingsPriceRangeTuple.
     * @param borough The Borough that the stage  is for.
     * @param listings The listings that the stage is for.
     * @param priceRange The PriceRange that the stage is for.
     */
    public BoroughListingsPriceRangeTuple(Borough borough, List<AirbnbListing> listings, PriceRange priceRange)
    {
        this.borough = borough;
        this.listings = listings;
        this.priceRange = priceRange;
    }

    /**
     * @return The borough.
     */
    public Borough getBorough()
    {
        return borough;
    }

    /**
     * @return The listings.
     */
    public List<AirbnbListing> getListings()
    {
        return listings;
    }

    /**
     * @return The price range.
     */
    public PriceRange getPriceRange()
    {
        return priceRange;
    }

    /**
     * The equals method now determines equality based off the Borough, List of AirbnbListing's and the PriceRange.
     * @param object The object to test if its equal to this instance.
     */
    @Override
    public boolean equals(Object object)
    {
        if (object == null || !(object instanceof BoroughListingsPriceRangeTuple))
        {
            return false;
        }

        BoroughListingsPriceRangeTuple otherBoroughListingsPriceRangeTuple = (BoroughListingsPriceRangeTuple) object;

        try
        {
            Objects.requireNonNull(this.borough);
            Objects.requireNonNull(this.listings);
            Objects.requireNonNull(this.priceRange);
            Objects.requireNonNull(otherBoroughListingsPriceRangeTuple.getBorough());
            Objects.requireNonNull(otherBoroughListingsPriceRangeTuple.getListings());
            Objects.requireNonNull(otherBoroughListingsPriceRangeTuple.getPriceRange());

            boolean boroughsMatch = this.borough.equals(otherBoroughListingsPriceRangeTuple.getBorough());
            boolean listingsMatch = this.listings.equals(otherBoroughListingsPriceRangeTuple.getListings());
            boolean priceRangesMatch = this.priceRange.equals(otherBoroughListingsPriceRangeTuple.getPriceRange());

            if (boroughsMatch && listingsMatch && priceRangesMatch)
            {
                return true;
            }
        }
        catch (NullPointerException e)
        {
            return false;
        }

        return false;
    }

    /**
     * The hash code is now based upon the from and to value.
     * @return The hash code.
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37 * result + listings.hashCode();
        result = 37 * result + borough.hashCode();
        result = 37 * result + priceRange.hashCode();
        return result;
    }
}