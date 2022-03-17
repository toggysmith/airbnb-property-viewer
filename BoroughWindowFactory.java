import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Write a description of class PropertyWindowFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoroughWindowFactory
{
    private static BoroughWindowFactory boroughWindowFactory;
    private MainWindow mainWindow;
    private WindowHashSet<BoroughWindow> openBoroughWindows;

    public BoroughWindowFactory()
    {
        openBoroughWindows = new WindowHashSet<>();
        mainWindow = MainWindow.getMainWindow();
    }
    
    public static BoroughWindowFactory getBoroughWindowFactory()
    {
        if (boroughWindowFactory == null)
        {
            boroughWindowFactory = new BoroughWindowFactory();
        }
        return boroughWindowFactory;
    }
    
    public BoroughWindow newBoroughWindowWithListings(Borough borough, List<AirbnbListing> listings)
    {
        BoroughWindow boroughWindow = checkWindow(borough, FXCollections.observableList(listings));
        return boroughWindow;
    }
    
    public BoroughWindow newBoroughWindow(Borough borough)
    {
        ObservableList<AirbnbListing> listings = FXCollections.observableList(mainWindow.getListingsInBorough(borough.NAME));
        BoroughWindow boroughWindow = checkWindow(borough, listings);
        return boroughWindow;
    }
    
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
    
    public void boroughWindowClosed(BoroughWindow boroughWindow)
    {
        openBoroughWindows.remove(boroughWindow);
    }
}
