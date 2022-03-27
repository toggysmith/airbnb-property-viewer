import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * This acts as a port for creating new windows to display
 * details about the properties in a borough.
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
    
    /**
     * Constructor for BoroughWindow.
     * @param boroughPriceRange The price range and borough that this
     * window is to diplay info about.
     */
    public BoroughWindow(BoroughPriceRange boroughPriceRange)
    {
        this.boroughPriceRange = boroughPriceRange;
    }
    
    /**
     * This creates the visible window and populates it with its info.
     * @param listingsInBorough The AirbnbListings that will be displayed in this window.
     */
    public void createBoroughWindow(ObservableList<AirbnbListing> listingsInBorough)
    {
        PriceRange priceRange = boroughPriceRange.getPriceRange();
        listings = FXCollections.observableList(listingsInBorough);
        try
        {
            boroughView = new BoroughWindowView(boroughPriceRange.getBorough().getName(), listings, this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * This calls for a property window to be created.
     * @param listing The AirbnbLising that the window will display.
     */
    public void createPropertyWindow(AirbnbListing listing)
    {
        PropertyWindowFactory.getPropertyWindowFactory().newPropertyWindow(listing);
    }
    
    /**
     * This tells the borough window factory that this window has been closed.
     */
    public void windowClosed()
    {
        BoroughWindowFactory.getBoroughWindowFactory().boroughWindowClosed(this);
    }
    
    /**
     * This sets its window to the front of the screen.
     */
    public void setFront()
    {
        boroughView.toFront();
    }
    
    /**
     * @return The boroughPriceRange.
     */
    public BoroughPriceRange getBoroughPriceRange()
    {
        return boroughPriceRange;
    }
    
    /**
     * @return The from price for this borough window as a String.
     */
    public String getFromPrice()
    {
        return boroughPriceRange.getPriceRange().getFromValueStr();
    }
    
    /**
     * @return The to price for this borough window as a String.
     */
    public String getToPrice()
    {
        return boroughPriceRange.getPriceRange().getToValueStr();
    }
    
    /**
     * Checks if the given object is the same as this borough window
     * based upon its boroughPriceRange which stores the borough and
     * the price range at the time when this object was created.
     * @param object The object to test if its equal.
     */
    @Override
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
    
    /**
     * This calucalates the hash code based upon the boroughPriceRange.
     * @return The hash code.
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37 * 17 + getBoroughPriceRange().hashCode();
        return result;
    }
}
