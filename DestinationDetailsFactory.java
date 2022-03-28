import java.util.Map;
import java.util.HashMap;

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
public class DestinationDetailsFactory
{
    private static DestinationDetailsFactory destinationWindowFactory;
    
    //The set of all open destination windows
    private Map<DestinationListing, DestinationDetailsStage> openDestinationWindows;
    
    /*
     * Constructor for DestinationWindowFactory,
     * Its private as this is a singleton.
     */
    private DestinationDetailsFactory()
    {
        openDestinationWindows = new HashMap<>();
    }
    
    /**
     * The method allows other classes to retrive the only object of
     * DestinationWindowFactory and ensures that only one object of this
     * class is ever created.
     * @return The only object of DestinationWindowFactory.
     */
    public static DestinationDetailsFactory getDestinationWindowFactory()
    {
        if (destinationWindowFactory == null)
        {
            destinationWindowFactory = new DestinationDetailsFactory();
        }
        return destinationWindowFactory;
    }
    
    /**
     * This method attempts to create a destination window for the listing given.
     * @param listing The listing for the destination you want a window for.
     */
    public DestinationDetailsStage newDestinationWindow(DestinationListing listing)
    {
        if (listing == null)
        {
            return null;
        }
        
        DestinationDetailsStage boroughDetailsStage = openDestinationWindows.get(listing);

        if (boroughDetailsStage != null)
        {
            boroughDetailsStage.toFront();

            return boroughDetailsStage;
        }

        try
        {
            boroughDetailsStage = new DestinationDetailsStage(listing);

            openDestinationWindows.put(listing, boroughDetailsStage);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            AlertManager.showTerminatingError("Could not create stage.");
        }

        return boroughDetailsStage;
    }
    
    /**
     * This method removes a destination window from the set of open windows.
     * @param destinationWindow The destination window closed.
     */
    public void destinationWindowClosed(DestinationDetailsStage destinationWindow)
    {
        openDestinationWindows.remove(destinationWindow);
    }
}
