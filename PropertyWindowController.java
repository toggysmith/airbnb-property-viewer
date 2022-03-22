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
        //webEngine.load("https://www.bing.com/maps/embed?h=400&w=500&cp=51.48577030797654~-0.09616984135084294&lvl=10.73468731310459&typ=d&sty=r&src=SHELL&FORM=MBEDV8");
        webEngine.getLoadWorker().stateProperty().addListener(e -> thing(webEngine));

    }

    public void thing(WebEngine webEngine)
    {
        ChangeListener listener = new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    webEngine.executeScript("setLocation(" + latitude + "," + longitude + ")");
                }
            };
        listener.changed(null, null, null);
    }
}
