import java.util.ArrayList;
import java.util.List;

 /**
 * Concrete implemenation of the PriceType used for representing the prices that can be given to the DestinationListing tourist attractions
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class AttractionPrice extends PriceType
{
    /**
     * Constructor to set up valid attraction destinations prices strings.
     */
    public AttractionPrice()
    {
        setupAttractionsPrices();
    }
    
    /*
     * Sets up the tourist attraction prices list of strings with the valid values.
     */
    private void setupAttractionsPrices()
    {
        destinationPrices.add("free");
        destinationPrices.add("£2.50 - £5.00");
        destinationPrices.add("£5.00 - £7.00");
        destinationPrices.add("£7.00 - £9.00");
    }
    
    /**
     * Checks whether the entered string is of valid price type, specifically for the tourist attractions.
     * @param priceString A string whose validity in relation to the concrete price type needs to be checked.
     * @return Whether the entered string is of valid price type.
     */
    protected boolean checkPriceValid(String priceString)
    {
        return super.checkPriceValid(priceString);
    }
}
