/**
 * DestinationWindow acts as a port for creating 
 * a new visible window to show a destination.
 * This class mainly allows for the factory to
 * test if a window for a given destination already exists.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class DestinationWindow
{
    private DestinationListing listing;
    private DestinationWindowView destinationView;
    
    /**
     * Constructor for objects of class DestinationWindow.
     * @param listing The AirbnbListing that this window will be displaying.
     */
    public DestinationWindow(DestinationListing listing)
    {
        this.listing = listing;
    }
    
    /**
     * This method creates the visible window.
     */
    public void createDestinationWindow()
    {
        try
        {
            destinationView = new DestinationWindowView(listing, this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * This method returns the AirbnbListing for this window.
     * @return The AirbnbListing for this window.
     */
    public DestinationListing getListing()
    {
        return listing;
    }
    
    /**
     * Sets this window to the frot of the users screen.
     */
    public void setFront()
    {
        destinationView.toFront();
    }
    
    /**
     * Alerts the DestinationWindowFactory that this window has been closed.
     */
    public void windowClosed()
    {
        DestinationWindowFactory.getDestinationWindowFactory().destinationWindowClosed(this);
    }
    
    /**
     * Checks if a given object is equal to this one.
     * It determines if the objects are equals by testing
     * if they are both of type DestinationWindow and if so
     * if they contain the same DestinationListing.
     * @return True if the objects are the same, othewise false.
     */
    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof DestinationWindow))
        {
            return false;
        }
        DestinationWindow destinationWindow = (DestinationWindow) object;
        return listing.equals(destinationWindow.getListing());
    }
    
    /**
     * The hash code for this object.
     * @return The hash code for this object.
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37 * 17 + listing.hashCode();
        return result;
    }

}
