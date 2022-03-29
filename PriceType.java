import java.util.List;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
/**
 * Write a description of class PriceType here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class PriceType
{
    protected List<String> destinationPrices;
    
    public PriceType()
    {
        destinationPrices = new ArrayList<>();
    }
    
    protected boolean checkPriceValid(String priceString)
    {
        if(priceString == null){
            return false;
        }else{
            for(int i = 0; i <= destinationPrices.size() - 1; i++){
                if(priceString.equals(destinationPrices.get(i))){
                    return true;
                }
            }
            return false;
        }
    }
    
    public String getValidPrice()
    {
        return destinationPrices.get(0);
    }
}
