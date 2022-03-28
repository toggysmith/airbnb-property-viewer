import java.util.Objects;
import java.util.List;

public class BoroughListingsPriceRangeTuple
{
    private Borough borough;
    private List<AirbnbListing> listings;
    private PriceRange priceRange;

    public BoroughListingsPriceRangeTuple(Borough borough, List<AirbnbListing> listings, PriceRange priceRange)
    {
        this.borough = borough;
        this.listings = listings;
        this.priceRange = priceRange;
    }

    public Borough getBorough()
    {
        return borough;
    }

    public List<AirbnbListing> getListings()
    {
        return listings;
    }

    public PriceRange getPriceRange()
    {
        return priceRange;
    }

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