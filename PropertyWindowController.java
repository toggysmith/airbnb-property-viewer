// @TODO: Refactor class
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import java.net.URL;

/**
 * Write a description of class PropertyWindowController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PropertyWindowController extends Controller
{
    @FXML private WebView mapsView;
    double latitude;
    double longitude;

    public void initialise(AirbnbListing listing)
    {
        latitude = listing.getLatitude();
        longitude = listing.getLongitude();

        URL pathToFile = getClass().getClassLoader().getResource("property-map.html");
        WebEngine webEngine = mapsView.getEngine();
        webEngine.load(pathToFile.toExternalForm());
        webEngine.getLoadWorker().stateProperty().addListener(e -> thing(webEngine));

    }

    public void thing(WebEngine webEngine)
    {
        ChangeListener listener = new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    Double num = (Double) webEngine.executeScript("setLocation(" + latitude + "," + longitude + ")");
                    System.out.println(num);
                }
            };
        listener.changed(null, null, null);
    }
}
