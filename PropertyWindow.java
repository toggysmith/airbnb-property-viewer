
/**
 * Write a description of class PropertyWindow here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PropertyWindow
{
    private AirbnbListing listing;
    private PropertyWindowView propertyView;
    /**
     * Constructor for objects of class PropertyWindow
     */
    public PropertyWindow(AirbnbListing listing)
    {
        this.listing = listing;
    }
    
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
    
    public AirbnbListing getListing()
    {
        return listing;
    }
    
    public void setFront()
    {
        propertyView.toFront();
    }
    
    public void windowClosed()
    {
        PropertyWindowFactory.getPropertyWindowFactory().propertyWindowClosed(this);
    }
    
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
    
    public int hashCode()
    {
        int result = 17;
        result = 37 * 17 + listing.hashCode();
        return result;
    }

}
