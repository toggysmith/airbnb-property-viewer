
/**
 * Write a description of class RangeValues here.
 *
 * @author (Augusto Favero K21059800)
 * @version (a version number or a date)
 */
public class RangeValues
{
    String fromValue;
    String toValue;

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
}
