import java.util.List;
import javafx.collections.ObservableList;


/**
 * Responsible for holding the position of a listing.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class MainWindow
{
    private static MainWindow mainWindow;
    
    private List<AirbnbListing> airbnbListings;
    private MainController mainController;
    private MainView mainView;
    private RangeValues rangeValues;
    
    /*
     * Constructor for objects of class MainWindow
     */
    private MainWindow()
    {
        airbnbListings = AirbnbDataLoader.getListings();
        try
        {
            mainView = new MainView(this);
            mainController = mainView.getMainController();
            rangeValues = mainController.getRangeValues();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @return The mainController.
     */
    public MainController getMainController()
    {
        return mainController;
    }
    
    /**
     * @return The mainView.
     */
    public MainView getMainView()
    {
        return mainView;
    }
    
    /**
     * This allows MainWindow to remain a singleton.
     * @return The MainWindow.
     */
    public static MainWindow getMainWindow()
    {
        if (mainWindow == null)
        {
            mainWindow = new MainWindow();
        }
        return mainWindow;
    }
    
    /**
     * @return The rangeValues.
     */
    public RangeValues getRangeValues()
    {
        return rangeValues;
    }
}
