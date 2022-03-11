import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

/**
 * Create the initial window.
 * 
 * @author Tony Smith (K21064940)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Adam Murray (K21003575)
 * @version 1.0
 */
public class MainController extends Application
{
    ArrayList<AirbnbListing> airbnbListings;
    
    private final int RANGE_BOX_STEP = 250;
    
    @FXML
    private ComboBox fromRangeBox;
    
    /**
     * Initialise instance variables.
     */
    public MainController()
    {
        airbnbListings = new AirbnbDataLoader().load();
    }
    
    /**
     * Create the initial window by loading the FXML file.
     * 
     * @param stage The JavaFX stage.
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Airbnb Property Viewer");
        stage.show();
    }
    
    /**
     * Get a list of possible price options for the range boxes.
     * 
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
     * 
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
     * 
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