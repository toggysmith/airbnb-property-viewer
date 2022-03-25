// @TODO: Refactor class

/**
 * Responsible for holding a selected borough and selected price range.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class BoroughPriceRange
{
    // instance variables - replace the example below with your own
    private Borough borough;
    private PriceRange priceRange;

    /**
     * Constructor for objects of class BoroughPriceRange
     */
    public BoroughPriceRange(Borough borough, PriceRange priceRange)
    {
        this.borough = borough;
        this.priceRange = priceRange;
    }
    
    public Borough getBorough()
    {
        return borough;
    }
    
    public PriceRange getPriceRange()
    {
        return priceRange;
    }
    
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof BoroughPriceRange))
        {
            return false;
        }
        BoroughPriceRange boroughPriceRange = (BoroughPriceRange) object;
        return (getBorough().equals(boroughPriceRange.getBorough())) && (getPriceRange().equals(boroughPriceRange.getPriceRange()));
    }
    
    public int hashCode()
    {
        int result = 17;
        result = 37 * 17 + getBorough().hashCode();
        result = 37 * 17 + getPriceRange().hashCode();
        return result;
    }
}
