

/**
 * RangeValues class represents the values that can selected by the user in the price range combo boxes in the main pane. The combo boxes can have the values "no min" and "no max" therefore this class handles the logic behind the
 * setting and retrieval of the fromValue and toValue Strings.
 * 
 * If "no min" is selected by the user, this value corresponds to the price "0", if "no max" is selected by the user this corresponds to the Integer.MAXVALUE value for the price
 * 
 * To faciliate the retrieval and setting of this data, the setter methods require the string selected from the combo boxes to be passed, however the retrieval of these strings converts the strings to the relevant integer
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
*/
public class RangeValues
{
    private String fromValue;
    private String toValue;

    /**
     * Constructor for objects of class RangeValues, storing the toValue and fromValue
     * @param String fromValue, the fromValue that represents the lowest possible value for the price attribute that an AirbnbListing can take in the valid range
     * @param String toValue, the toValue that represents the highest possible value for the price attribute that an AirbnbListing can take in the valid range
     */
    public RangeValues(String fromValue, String toValue)
    {
        this.fromValue = fromValue;
        this.toValue = toValue;
    }
    
    /**
     * Method to set the fromValue String
     * @param String fromValue, the value that represents the lowest possible price that an AirbnbListing can take in the valid range
     */
    public void setFromValue(String fromValue)
    {
        this.fromValue = fromValue;
    }
    
    /*
     * Method to set the toValue String
     * @param String toValue, the value that represents the highest possible price that an AirbnbListing can take in the valid range
     * 
     */
    public void setToValue(String toValue)
    {
        this.toValue = toValue;
    }
    
    /*
     * Returns the fromValue, facilitates exteranl functionalities by encapsulating conversion logic, the from value string is converted into its corresponding integer and returned.
     * @return int, the from value string converted into an integer
     */
    public int getFromValue()
    {
        return convertFromStrToInt(this.fromValue);
    }
    
    /*
     * Returns the toValue, facilitates exteranl functionalities by encapsulating conversion logic, the to value string is converted into its corresponding integer and returned.
     * @return int, the to value string converted into an integer
     */
    public int getToValue()
    {
        return convertToStrToInt(this.toValue);
    }
    
    /*
     * method that handles the conversion logic for the fromValue string into its corresponding integer.
     * In the case the fromValue selected is  "no min" , 0 is returned
     * 
     * @return int
     */
    public int convertFromStrToInt(String fromValue)
    {
        if(fromValue.equals(RangeBoxEnum.NOMIN.toString())){
            return 0;
        }else{
            return Integer.parseInt(fromValue);
        }
    }
    
    /*
     * method that handles the conversion logic for the toValue string into its corresponding integer
     * In the case the toValue selected is "no max" , Integer.MAXVALUE is returned
     */
    public int convertToStrToInt(String toValue)
    {
        if(toValue.equals(RangeBoxEnum.NOMAX.toString())){
            return Integer.MAX_VALUE;
        }else{
            return Integer.parseInt(toValue);
        }
    }
    
    /**
     * @return A new PriceRange with the values of the this rangeValues.
     */
    public PriceRange getPriceRange()
    {
        return new PriceRange(fromValue, toValue);
    }
    
    /*
     * method that handles the conversion logic for the fromValue integer returned by the .getFromValue() into its corresponding string. Mainly used in the revert stage when an invalid range is selected and the from combobox needs the
     * corresponding fromValue string to revert the state
     * @return String
     */
    public String convertFromIntToStr(int fromValue)
    {
        if(fromValue == 0){
            return RangeBoxEnum.NOMIN.toString();
        }else{
            return Integer.toString(fromValue);
        }
    }
    
    /*
     * method that handles the conversion logic for the toValue integer returned by the .getToValue() into its corresponding string. Mainly used in the revert stage when an invalid range is selected and the to combobox needs the
     * corresponding toValue string to revert the state
     * @return String
     */
    public String convertToIntToStr(int toValue)
    {
        if(toValue == Integer.MAX_VALUE){
            return RangeBoxEnum.NOMAX.toString();
        }else{
            return Integer.toString(toValue);
        }
    }
}
