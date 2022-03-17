import java.util.List;
import javafx.collections.ObservableList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Write a description of class BoroughWindow here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoroughWindow
{
    private BoroughWindowView boroughView;
    private ObservableList<AirbnbListing> listings;
    private BoroughPriceRange boroughPriceRange;
    
    public BoroughWindow(BoroughPriceRange boroughPriceRange)
    {
        this.boroughPriceRange = boroughPriceRange;
        listings = MainWindow.getMainWindow().getListingsInBorough(boroughPriceRange.getBorough().NAME, boroughPriceRange.getPriceRange());
        try
        {
            boroughView = new BoroughWindowView(boroughPriceRange.getBorough().NAME, listings, this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void createPropertyWindow(AirbnbListing listing)
    {
        PropertyWindowFactory.getPropertyWindowFactory().newPropertyWindow(listing);
    }
    
    public void windowClosed()
    {
        BoroughWindowFactory.getBoroughWindowFactory().boroughWindowClosed(this);
    }
    
    public void setFront()
    {
        boroughView.toFront();
    }
    
    public BoroughPriceRange getBoroughPriceRange()
    {
        return boroughPriceRange;
    }
    
    public String getFromPrice()
    {
        return boroughPriceRange.getPriceRange().getFromValueStr();
    }
    
    public String getToPrice()
    {
        return boroughPriceRange.getPriceRange().getToValueStr();
    }
    
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof BoroughWindow))
        {
            return false;
        }
        BoroughWindow boroughWindow = (BoroughWindow) object;
        return (getBoroughPriceRange().equals(boroughWindow.getBoroughPriceRange()));
    }
    
    public int hashCode()
    {
        int result = 17;
        result = 37 * 17 + getBoroughPriceRange().hashCode();
        return result;
    }
}
