import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 * MainView creates the primary application window by loading it from
 * an FXML file. It also uses the AirbnbDataLoader to load the Airbnb
 * listings from the database.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public class MainView extends Stage
{
    // The listings which get loaded from the database.
    ArrayList<AirbnbListing> airbnbListings;
    
    // The step in which the range box selector goes up.
    // (E.g. if min property price is 0 and the max is 6000 and the step is
    // 500, then the options would be 0, 500, 1000, 1500, 2000, 2500, etc)
    private final int RANGE_BOX_STEP = 250;
    
    /**
     * Create a window and load the FXML file.
     */
    public MainView() throws Exception
    {
        airbnbListings = new AirbnbDataLoader().load();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        
        Scene scene = new Scene(loader.load());
        
        MainController mainController = loader.getController();
        
        mainController.fromRangeBox.getItems().addAll(getRangeBoxOptions("No min"));
        mainController.toRangeBox.getItems().addAll(getRangeBoxOptions("No max"));
        
        setScene(scene);
        setTitle("Airbnb Property Viewer");
        show();
    }
    
    /**
     * Get a list of possible price options for the range boxes.
     * @param noOptionString The string to show to select no option, e.g. "No min".
     * @return The list of possible price options for the range boxes.
     */
    public ArrayList<String> getRangeBoxOptions(String noOptionString)
    {
        ArrayList<String> options = new ArrayList<>();
        
        for (int currentPrice = 0; currentPrice <= getMaxPropertyPrice(); currentPrice += RANGE_BOX_STEP)
        {
            if (currentPrice == 0)
            {
                options.add(noOptionString);
            }
            else
            {
                options.add(Integer.toString(currentPrice));
            }
        }
        
        return options;
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
}