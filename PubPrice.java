import java.util.List;
import java.util.ArrayList;
/**
 * Concrete implemenation of the PriceType used for representing the prices that can be given to the DestinationListing for pubs
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0

 */
public class PubPrice extends PriceType
{
    /**
     * Constructor to set up valid pub prices strings
     */
    public PubPrice()
    {
        super();
        setUpPubPrices();
    }
    
    /*
     * Sets up the pub prices list of strings with the valid values
     */
    private void setUpPubPrices()
    {
        destinationPrices.add("£");
        destinationPrices.add("££");
        destinationPrices.add("£££");
    }
    
    /**
     * checks whether the entered string is of valid price type, specifically for the pubs
     * @param String priceString, a string whose validity in relation to the concrete price type needs to be checked
     * @return boolean, whether the entered string is of valid price type
     */
    protected boolean checkPriceValid(String priceString)
    {
        return super.checkPriceValid(priceString);
    }
    
    
}
