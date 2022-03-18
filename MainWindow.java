import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;


/**
 * Write a description of class MainWindow here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainWindow
{
    private static MainWindow mainWindow;
    
    private List<AirbnbListing> airbnbListings;
    private MainController mainController;
    private RangeValues rangeValues;
    /**
     * Constructor for objects of class MainWindow
     */
    private MainWindow()
    {
        airbnbListings = AirbnbDataLoader.getListings();
        try
        {
            MainView mainView = new MainView(this);
            mainController = mainView.getMainController();
            rangeValues = mainController.getRangeValues();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static MainWindow getMainWindow()
    {
        if (mainWindow == null)
        {
            mainWindow = new MainWindow();
        }
        return mainWindow;
    }
    
    /**
     * Get the minimum property price.
     * @return The minimum property price.
     */
    public int getMinPropertyPrice()
    {
        return airbnbListings.stream()
                             .map(listing -> listing.getPrice())
                             .min(Integer::compare)
                             .get();
    }
    
    /**
     * Get the maximum property price.
     * @return The maximum property price.
     */
    public int getMaxPropertyPrice()
    {
        return airbnbListings.stream()
                             .map(listing -> listing.getPrice())
                             .max(Integer::compare)
                             .get();
    }
    
    public RangeValues getRangeValues()
    {
        return rangeValues;
    }
    
    
    /**
     * Get the listings in a specific borough.
     * @return The listings in a specific borough.
     */
    public ObservableList<AirbnbListing> getListingsInBorough(String targetBorough, PriceRange priceRange)
    {
        List<AirbnbListing> listings = AirbnbDataLoader.getListings();
        
        int fromValue = priceRange.getFromValue();
        int toValue = priceRange.getToValue();
        
        listings = ListingManipulator.filterByPriceRange(ListingManipulator.filterByBorough(listings, targetBorough),
                                                         fromValue,
                                                         toValue);
        
        ObservableList<AirbnbListing> returnListings = FXCollections.observableArrayList();

        for (AirbnbListing listing : listings)
            returnListings.add(listing);
        
        return returnListings;
    }
    
  
}
