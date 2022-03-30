import java.util.List;
import java.util.ArrayList;

/**
 * PriceType class is used to represent the possible price values that different DestinationListing objects can take. Specifically
 * pubs can have a price value of (£ or ££ or £££) whilst attractions can have ticket prices (free, £2.50 - £5.00, £5.00 - £7.00,
 * £7.00 - £9.00)
 * 
 * Makes extension to new types of prices possible.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public abstract class PriceType
{
    /**
     * List storing the possible price values, this is initaliased by the concrete classes of PriceType.
     */
    protected List<String> destinationPrices;
    
    /**
     * Constructor that intialises the destinationPrices list, without adding anything, this behaviour is delegated to concrete classes
     */
    public PriceType()
    {
        destinationPrices = new ArrayList<>();
    }
    
    /**
     * Checks whether the entered string is of valid price type, this depends on the concrete implementation of this class
     * @param priceString A string whose validity in relation to the concrete price type needs to be checked
     * @return Whetherthe entered string is of valid price type.
     */
    protected boolean checkPriceValid(String priceString)
    {
        if (priceString == null)
        {
            return false;
        } else {
            for (int i = 0; i <= destinationPrices.size() - 1; i++)
            {
                if (priceString.equals(destinationPrices.get(i)))
                {
                    return true;
                }
            }
            
            return false;
        }
    }
    
    /**
     * @return The first valid price string from the list of valid price strings.
     */
    protected String getValidPrice()
    {
        return destinationPrices.get(0);
    }
    
    /**
     * @return The list of valid prices (depending on the implementation).
     */
    protected List<String> getPrices()
    {
        return destinationPrices;
    }
}
