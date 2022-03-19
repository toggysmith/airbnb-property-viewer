// @TODO: Refactor class

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Write a description of class BoroughWindow here.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class BoroughWindow
{
    private BoroughWindowView boroughView;
    private ObservableList<AirbnbListing> listings;
    private BoroughPriceRange boroughPriceRange;
    
    public BoroughWindow(BoroughPriceRange boroughPriceRange)
    {
        this.boroughPriceRange = boroughPriceRange;
    }
    
    public void createBoroughWindow(ObservableList<AirbnbListing> listingsInBorough)
    {
        PriceRange priceRange = boroughPriceRange.getPriceRange();
        listings = FXCollections.observableList(ListingManipulator.filterByPriceRange(listingsInBorough, priceRange.getFromValue(), priceRange.getToValue()));
        try
        {
            boroughView = new BoroughWindowView(boroughPriceRange.getBorough().getName(), listings, this);
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
