/**
 * DestinationWindowFactory controls the creation of destination windows.
 * Tts main responsibility is to ensure that only one destination window exists
 * for each destination at any given time.
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
    
    //The set of all open destination windows
    private WindowHashSet<DestinationWindow> openDestinationWindows;
    
    /*
     * Constructor for DestinationWindowFactory,
     * Its private as this is a singleton.
     */
    private DestinationWindowFactory()
    {
        openDestinationWindows = new WindowHashSet<>();
    }
    
    /**
     * The method allows other classes to retrive the only object of
     * DestinationWindowFactory and ensures that only one object of this
     * class is ever created.
     * @return The only object of DestinationWindowFactory.
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
     * This method attempts to create a destination window for the listing given.
     * @param listing The listing for the destination you want a window for.
     */
    public DestinationWindow newDestinationWindow(DestinationListing listing)
    {
        if (listing == null)
        {
            return null;
        }
        
        DestinationWindow destinationWindow = new DestinationWindow(listing); //This creates an object of DestinationWindow, not DestinationWindowView and so does not create the actual window.
        if (!(openDestinationWindows.add(destinationWindow)))
        {
            destinationWindow = openDestinationWindows.getElementInSet(destinationWindow); //Gets the DestinationWindow from the set that is equivalent to the DestinationWindow just created.
        }
        else
        {
            destinationWindow.createDestinationWindow(); // This line creates the actual destination window once it has been determined that no such window already exists.
        }
        destinationWindow.setFront(); // Sets the window to the front of the screen.
        return destinationWindow;
    }
    
    /**
     * This method removes a destination window from the set of open windows.
     * @param destinationWindow The destination window closed.
     */
    public void destinationWindowClosed(DestinationWindow destinationWindow)
    {
        openDestinationWindows.remove(destinationWindow);
    }
}
