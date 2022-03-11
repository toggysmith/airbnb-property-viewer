import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.collections.ObservableList;


public class MainView extends Stage
{
    ArrayList<AirbnbListing> airbnbListings;
    
    private final int RANGE_BOX_STEP = 250;
    
    public MainView() throws Exception
    {
        airbnbListings = new AirbnbDataLoader().load();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        
        Scene scene = new Scene(loader.load());
        
        MainController mainController = loader.getController();
        
        mainController.fromRangeBox.setItems(getRangeBoxOptions("No min"));
        mainController.toRangeBox.setItems(getRangeBoxOptions("No max"));
        
        setScene(scene);
        setTitle("Airbnb Property Viewer");
        show();
    }
    
    /**
     * Get a list of possible price options for the range boxes.
     * 
     * @param noOptionString The string to show to select no option, e.g. "No min".
     * @return The list of possible price options for the range boxes.
     */
    public ObservableList<String> getRangeBoxOptions(String noOptionString)
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
        
        return FXCollections.observableList(options);
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