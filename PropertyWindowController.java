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
    
    private OpenLayersMap openLayersMap;

    private double longitude;
    private double latitude;

    
    public void initialise(AirbnbListing listing)
    {   
        longitude = listing.getLongitude();
        latitude = listing.getLatitude();
        openLayersMap = new OpenLayersMap("resources/open-layers-map/property-map.html", longitude, latitude);
        borderPane.setCenter(openLayersMap);
        
        addPropertiesToJsFile(listing);
    }
    
    private void addPropertiesToJsFile(AirbnbListing listing)
    {
            String id = listing.getId();
            int price = listing.getPrice();

            String jsObject = String.format("{id: %s, longitude: %f, latitude: %f, price: %d}", id, longitude, latitude, price);
            String jsScript = String.format("addProperty(%s)", jsObject);

            openLayersMap.executeScript(jsScript, true);
    }
}
