import java.util.Map;
import java.util.HashMap;

/**
 * Write a description of class PropertyWindowFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PropertyWindowFactory
{
    private static PropertyWindowFactory propertyWindowFactory;
    
    private Map<AirbnbListing, PropertyWindow> openPropertyWindows;

    public PropertyWindowFactory()
    {
        openPropertyWindows = new HashMap<>();
    }
    
    public static PropertyWindowFactory getPropertyWindowFactory()
    {
        if (propertyWindowFactory == null)
        {
            propertyWindowFactory = new PropertyWindowFactory();
        }
        return propertyWindowFactory;
    }
    
    public PropertyWindow newPropertyWindow(AirbnbListing listing)
    {
        PropertyWindow propertyWindow = openPropertyWindows.get(listing);
        if (propertyWindow == null)
        {
            propertyWindow = new PropertyWindow(listing);
            openPropertyWindows.put(listing, propertyWindow);
        }
        propertyWindow.setFront();
        return propertyWindow;
    }
    
    public void propertyWindowClosed(PropertyWindow propertyWindow)
    {
        openPropertyWindows.remove(propertyWindow.getListing());
    }
}
