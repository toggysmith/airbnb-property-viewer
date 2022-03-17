
/**
 * Write a description of class PriceRange here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PriceRange
{
    private String fromValue;
    private String toValue;

    /**
     * Constructor for objects of class RangeValues
     */
    public PriceRange(String fromValue, String toValue)
    {
        this.fromValue = fromValue;
        this.toValue = toValue;
    }

    public int getFromValue()
    {
        if(fromValue.equals(RangeBoxEnum.NOMIN.toString())){
            return 0;
        }else{
            return Integer.parseInt(fromValue);
        }
    }
    
    public int getToValue()
    {
        if(toValue.equals(RangeBoxEnum.NOMAX.toString())){
            return Integer.MAX_VALUE;
        }else{
            return Integer.parseInt(toValue);
        }
    }
    
    public String getFromValueStr()
    {
        return this.fromValue;
    }
    
    public String getToValueStr()
    {
        return this.toValue;
    }
    
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
    
    public int hashCode()
    {
        int result = 17;
        result = 37 * 17 + getFromValue();
        result = 37 * 17 + getToValue();
        return result;
    }
}
