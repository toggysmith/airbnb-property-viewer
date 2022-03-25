/**
 * PropertyWindow acts as a port for creating 
 * a new visible window to show a property.
 * This class mainly allows for the factory to
 * test if a window for a given property already exists.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class PropertyWindow
{
    private AirbnbListing listing;
    private PropertyWindowView propertyView;
    
    /**
     * Constructor for objects of class PropertyWindow.
     * @param listing The AirbnbListing that this window will be displaying.
     */
    public PropertyWindow(AirbnbListing listing)
    {
        this.listing = listing;
    }
    
    /**
     * This method creates the visible window.
     */
    public void createPropertyWindow()
    {
        try
        {
            propertyView = new PropertyWindowView(listing, this);
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
    public AirbnbListing getListing()
    {
        return listing;
    }
    
    /**
     * Sets this window to the frot of the users screen.
     */
    public void setFront()
    {
        propertyView.toFront();
    }
    
    /**
     * Alerts the PropertyWindowFactory that this window has been closed.
     */
    public void windowClosed()
    {
        PropertyWindowFactory.getPropertyWindowFactory().propertyWindowClosed(this);
    }
    
    /**
     * Checks if a given object is equal to this one.
     * It determines if the objects are equals by testing
     * if they are both of type PropertyWindow and if so
     * if they contain the same AirbnbListing.
     * @return True if the objects are the same, othewise false.
     */
    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof PropertyWindow))
        {
            return false;
        }
        PropertyWindow propertyWindow = (PropertyWindow) object;
        return listing.equals(propertyWindow.getListing());
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
