/**
 * PropertyWindowFactory controls the creation of property windows.
 * Tts main responsibility is to ensure that only one property window exists
 * for each property at any given time.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class PropertyWindowFactory
{
    private static PropertyWindowFactory propertyWindowFactory;
    
    //The set of all open property windows
    private WindowHashSet<PropertyWindow> openPropertyWindows;

    // Constructor for PropertyWindowFactory,
    //Its private as this is a singleton.
    private PropertyWindowFactory()
    {
        openPropertyWindows = new WindowHashSet<>();
    }
    
    /**
     * The method allows other classes to retrive the only object of
     * PropertyWindowFactory and ensures that only one object of this
     * class is ever created.
     * @return The only object of PropertyWindowFactory.
     */
    public static PropertyWindowFactory getPropertyWindowFactory()
    {
        if (propertyWindowFactory == null)
        {
            propertyWindowFactory = new PropertyWindowFactory();
        }
        return propertyWindowFactory;
    }
    
    /**
     * This method takes the listingId given, finds the listing with that id,
     * and then attempts to create a property window for that listing.
     * @param listingId The id for the property you want a window for.
     */
    public PropertyWindow newPropertyWindow(String listingId)
    {
        AirbnbListing listing = ListingManipulator.getListingWithId(listingId);
        return newPropertyWindow(listing);
    }
    
    /**
     * This method attempts to create a property window for the listing given.
     * @param listing The listing for the property you want a window for.
     */
    public PropertyWindow newPropertyWindow(AirbnbListing listing)
    {
        if (listing == null)
        {
            return null;
        }
        
        PropertyWindow propertyWindow = new PropertyWindow(listing); //This creates an object of PropertyWindow, not PropertyWindowView and so does not create the actual window.
        if (!(openPropertyWindows.add(propertyWindow)))
        {
            propertyWindow = openPropertyWindows.getElementInSet(propertyWindow); //Gets the PropertyWindow from the set that is equivalent to the PropertyWindow just created.
        }
        else
        {
            propertyWindow.createPropertyWindow(); // This line creates the actual property window once it has been determined that no such window already exists.
        }
        propertyWindow.setFront(); // Sets the window to the front of the screen.
        return propertyWindow;
    }
    
    /**
     * This method removes a property window from the set of open windows.
     * @param propertyWindow The property window closed.
     */
    public void propertyWindowClosed(PropertyWindow propertyWindow)
    {
        openPropertyWindows.remove(propertyWindow);
    }
}
