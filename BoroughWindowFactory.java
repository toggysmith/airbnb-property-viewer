import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * BoroughWindowFactory manages the creation of new windows for the boroughs,
 * it ensures that only one window can exist for each borough for a specific 
 * price range at a given time.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class BoroughWindowFactory
{
    private static BoroughWindowFactory boroughWindowFactory;
    private MainWindow mainWindow;
    private WindowHashSet<BoroughWindow> openBoroughWindows;

    /**
     * Constructor for BoroughWindowFactory
     */ 
    private BoroughWindowFactory()
    {
        openBoroughWindows = new WindowHashSet<>();
        mainWindow = MainWindow.getMainWindow();
    }
    
    /**
     * Ensures that only one BoroughWindowFactory is ever created.
     * @return The only object of BoroughWindowFactory.
     */
    public static BoroughWindowFactory getBoroughWindowFactory()
    {
        if (boroughWindowFactory == null)
        {
            boroughWindowFactory = new BoroughWindowFactory();
        }
        return boroughWindowFactory;
    }
    
    /**
     * This method checks if the param listings is empty and if not attempts
     * to create a window for the borough.
     * @param borough The borough that the window is being created for.
     * @param listings The listings in the borough in the price range.
     * @return The BoroughWindow that is created.
     * @throws EmptyListException When the the param listings is empty.
     */
    public BoroughWindow newBoroughWindowWithListings(Borough borough, List<AirbnbListing> listings) throws EmptyListException
    {
        if (listings.size() == 0)
        {
            throw new EmptyListException();
        }
        BoroughWindow boroughWindow = checkWindow(borough, FXCollections.observableList(listings));
        return boroughWindow;
    }
    
    /**
     * This method checks if a boroughWindow for the given borough already exists,
     * if so it finds that window and sets it to the front of the screen,
     * if not it creates a new window for this borough
     */
    private BoroughWindow checkWindow(Borough borough, ObservableList<AirbnbListing> listings)
    {
        PriceRange priceRange = mainWindow.getRangeValues().getPriceRange();
        BoroughPriceRange boroughPriceRange = new BoroughPriceRange(borough, priceRange);

        BoroughWindow boroughWindow = new BoroughWindow(boroughPriceRange);
        if (!(openBoroughWindows.add(boroughWindow)))
        {
            boroughWindow = openBoroughWindows.getElementInSet(boroughWindow);
        }
        else
        {
            boroughWindow.createBoroughWindow(listings);
        }
        boroughWindow.setFront();
        return boroughWindow;
    }
    
    /**
     * Removes the specified boroughWindow from the set of open boroughWindows.
     * @param boroughWindow The BoroughWindow is be removed.
     */
    public void boroughWindowClosed(BoroughWindow boroughWindow)
    {
        openBoroughWindows.remove(boroughWindow);
    }
}
