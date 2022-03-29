import java.util.ArrayList;
import java.util.List;
/**
 * Enumeration class AttractionPriceEnum - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public class AttractionPrice extends PriceType
{
    public AttractionPrice()
    {
        super();
        setUpAttractionsPrices();
    }
    
    private void setUpAttractionsPrices()
    {
        destinationPrices.add("free");
        destinationPrices.add("£2.50 - £5.00");
        destinationPrices.add("£5.00 - £7.00");
        destinationPrices.add("£7.00 - £9.00");
    }
    
    protected boolean checkPriceValid(String priceString)
    {
        return super.checkPriceValid(priceString);
    }
}
