import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class DestinationPriceEnum here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PubPrice extends PriceType
{
    public PubPrice()
    {
        super();
        setUpPubPrices();
    }
    
    private void setUpPubPrices()
    {
        destinationPrices.add("£");
        destinationPrices.add("££");
        destinationPrices.add("£££");
    }
    
    protected boolean checkPriceValid(String priceString)
    {
        return super.checkPriceValid(priceString);
    }
}
