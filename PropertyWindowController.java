// @TODO: Refactor class
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import java.net.URL;
import javafx.scene.layout.BorderPane;

/**
 * Write a description of class PropertyWindowController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PropertyWindowController extends Controller
{
    @FXML private BorderPane borderPane;
    
    private OpenLayersMap openLayersMap = new OpenLayersMap("resources/open-layers-map/map.html", 16, -0.115937, 51.511437);

    private double longitude;
    private double latitude;

    @FXML
    public void initialize()
    {
        borderPane.setCenter(openLayersMap);
        
        openLayersMap.addBehaviour(OpenLayersMap.Behaviour.MARKER);
    }
    
    public void setup(AirbnbListing listing)
    {
        longitude = listing.getLongitude();
        latitude = listing.getLatitude();
        
        openLayersMap.executeScript(String.format("setLongLat(%f, %f)", longitude, latitude), true);
        
        addPropertiesToJsFile(listing);
    }
    
    private void addPropertiesToJsFile(AirbnbListing listing)
    {
        String id = listing.getId();
        double longitude = listing.getLongitude();
        double latitude = listing.getLatitude();
        int price = listing.getPrice();

        String jsObject = String.format("{id: %s, longitude: %f, latitude: %f, price: %d}", id, longitude, latitude, price);
        String jsScript = String.format("addMarker(%s);", jsObject);

        openLayersMap.executeScript(jsScript, true);
    }
}
