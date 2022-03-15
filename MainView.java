import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javafx.scene.layout.Pane;


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
    private static List<AirbnbListing> airbnbListings;
    
    /**
     * Create a window and load the FXML file.
     */
    public MainView() throws Exception
    {
        airbnbListings = AirbnbDataLoader.getListings();
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        
       Scene scene = new Scene(loader.load());
       mainController = loader.getController();
        
       //windowPanes.add(mainController.getWelcomePane());
       mainController.setUpPanes();
       
       
       
    
       
       mainController.fromRangeBox.getItems().addAll(getRangeBoxOptions(RangeBoxEnum.NOMIN));
       mainController.toRangeBox.getItems().addAll(getRangeBoxOptions(RangeBoxEnum.NOMAX));
        setScene(scene);
        setTitle("Airbnb Property Viewer");
        show();
    }
    
    /**
     * Get a list of possible price options for the range boxes.
     * @param noOptionString The string to show to select no option, e.g. "No min".
     * @return The list of possible price options for the range boxes.
     */
    public ArrayList<String> getRangeBoxOptions(RangeBoxEnum noOptionString)
    {
        ArrayList<String> options = new ArrayList<>();
        
        int stepAmount = 10;
        
        for (int currentPrice = 0; currentPrice <= getMaxPropertyPrice(); currentPrice += stepAmount)
        {
            if (currentPrice == stepAmount * 10)
            {
                stepAmount = stepAmount * 10;
            }
            
            if (currentPrice == 0)
            {
                options.add(noOptionString.toString());
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
    
    public static ObservableList<AirbnbListing> getListingsInBorough(String targetBorough)
    {
        Object[] listingsInBorough = airbnbListings.stream()
                             .filter(listing -> listing.getNeighbourhood().equals(targetBorough))
                             //.filter(listing -> listing.getPrice() > mainController.getFromComboValue())
                             //.filter(listing -> listing.getPrice() < mainController.getToComboValue())
                             .toArray();
        ObservableList<AirbnbListing> listListingsInBorough = FXCollections.observableArrayList();
        for (Object listing : listingsInBorough)
        {
            AirbnbListing listingAsAirBnb = (AirbnbListing)listing;
            listListingsInBorough.add(listingAsAirBnb);
        }
        return listListingsInBorough;
    }
}