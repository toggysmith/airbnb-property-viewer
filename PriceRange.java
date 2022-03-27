/**
 * This class is used to store the price range of the combo boxes
 * at the time this object was created.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */ 
public class PriceRange
{
    private String fromValue;
    private String toValue;

    /**
     * Constructor for objects of class PriceRange
     */
    public PriceRange(String fromValue, String toValue)
    {
        this.fromValue = fromValue;
        this.toValue = toValue;
    }

    /**
     * @return The from value as an int.
     */
    public int getFromValue()
    {
        if(fromValue.equals(RangeBoxEnum.NOMIN.toString())){
            return 0;
        }else{
            return Integer.parseInt(fromValue);
        }
    }
    
    /**
     * @return The to value as an int.
     */
    public int getToValue()
    {
        if(toValue.equals(RangeBoxEnum.NOMAX.toString())){
            return Integer.MAX_VALUE;
        }else{
            return Integer.parseInt(toValue);
        }
    }
    
    /**
     * @return The from value as an String.
     */
    public String getFromValueStr()
    {
        return this.fromValue;
    }
    
    /**
     * @return The to value as an String.
     */
    public String getToValueStr()
    {
        return this.toValue;
    }
    
    /**
     * Checks if a given object is same based on whether the
     * from and to values are the same.
     * @return True if the from and to values are the same, otherwise false.
     */
    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof PriceRange))
        {
            return false;
        }
        PriceRange priceRange = (PriceRange) object;
        return (getFromValue() == priceRange.getFromValue()) && (getToValue() == priceRange.getToValue());
    }
    
    /**
     * The hash code is now based upon the from and to value.
     * @return The hash code.
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37 * 17 + getFromValue();
        result = 37 * 17 + getToValue();
        return result;
    }
}
