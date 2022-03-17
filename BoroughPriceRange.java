
/**
 * Write a description of class BoroughPriceRange here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
