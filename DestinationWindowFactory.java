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
public class DestinationWindowFactory
{
    private static DestinationWindowFactory destinationWindowFactory;
    
    //The set of all open property windows
    private WindowHashSet<DestinationWindow> openDestinationWindows;

    // Constructor for PropertyWindowFactory,
    //Its private as this is a singleton.
    private DestinationWindowFactory()
    {
        openDestinationWindows = new WindowHashSet<>();
    }
    
    /**
     * The method allows other classes to retrive the only object of
     * PropertyWindowFactory and ensures that only one object of this
     * class is ever created.
     * @return The only object of PropertyWindowFactory.
     */
    public static DestinationWindowFactory getDestinationWindowFactory()
    {
        if (destinationWindowFactory == null)
        {
            destinationWindowFactory = new DestinationWindowFactory();
        }
        return destinationWindowFactory;
    }
    
    /**
     * This method attempts to create a property window for the listing given.
     * @param listing The listing for the property you want a window for.
     */
    public DestinationWindow newDestinationWindow(DestinationListing listing)
    {
        if (listing == null)
        {
            return null;
        }
        
        DestinationWindow destinationWindow = new DestinationWindow(listing); //This creates an object of PropertyWindow, not PropertyWindowView and so does not create the actual window.
        if (!(openDestinationWindows.add(destinationWindow)))
        {
            destinationWindow = openDestinationWindows.getElementInSet(destinationWindow); //Gets the PropertyWindow from the set that is equivalent to the PropertyWindow just created.
        }
        else
        {
            destinationWindow.createDestinationWindow(); // This line creates the actual property window once it has been determined that no such window already exists.
        }
        destinationWindow.setFront(); // Sets the window to the front of the screen.
        return destinationWindow;
    }
    
    /**
     * This method removes a property window from the set of open windows.
     * @param propertyWindow The property window closed.
     */
    public void destinationWindowClosed(DestinationWindow destinationWindow)
    {
        openDestinationWindows.remove(destinationWindow);
    }
}
