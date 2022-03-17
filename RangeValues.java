
/**
@author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
*/
public class RangeValues
{
    private String fromValue;
    private String toValue;

    /**
     * Constructor for objects of class RangeValues
     */
    public RangeValues(String fromValue, String toValue)
    {
        this.fromValue = fromValue;
        this.toValue = toValue;
    }
    
    public void setFromValue(String fromValue)
    {
        this.fromValue = fromValue;
    }
    
    public void setToValue(String toValue)
    {
        this.toValue = toValue;
    }
    
    public int getFromValue()
    {
        return convertFromStrToInt(this.fromValue);
    }
    
    public int getToValue()
    {
        return convertToStrToInt(this.toValue);
    }
    
    public String getFromValueStr()
    {
        return null;
    }
    public int convertFromStrToInt(String fromValue)
    {
        if(fromValue.equals(RangeBoxEnum.NOMIN.toString())){
            return 0;
        }else{
            return Integer.parseInt(fromValue);
        }
    }
    
    public int convertToStrToInt(String toValue)
    {
        if(toValue.equals(RangeBoxEnum.NOMAX.toString())){
            return Integer.MAX_VALUE;
        }else{
            return Integer.parseInt(toValue);
        }
    }
    

    public PriceRange getPriceRange()
    {
        return new PriceRange(fromValue, toValue);
    }
    
    public String convertFromIntToStr(int fromValue)
    {
        if(fromValue == 0){
            return RangeBoxEnum.NOMIN.toString();
        }else{
            return Integer.toString(fromValue);
        }
    }
    
    public String convertToIntToStr(int toValue)
    {
        if(toValue == Integer.MAX_VALUE){
            return RangeBoxEnum.NOMAX.toString();
        }else{
            return Integer.toString(toValue);
        }
    }
}
