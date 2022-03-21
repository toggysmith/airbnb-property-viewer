// @TODO: Refactor class
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
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
    
    
    public void initialise(AirbnbListing listing)
    {
        double latitude = listing.getLatitude();
        double longitude = listing.getLongitude();

        //String url = "https://www.google.com/maps/@?api=1&map_action=pano&viewpoint=" + listing.getLatitude() +  "," + listing.getLongitude();
        //String url = "https://www.google.com/maps/@" + listing.getLatitude() +  "," + listing.getLongitude() +  ",12.96z";
        //String url = "https://www.google.com/maps/search/?api=1&query=" + listing.getLatitude() +  "," + listing.getLongitude();
        
        /*String url = "https://www.openstreetmap.org/?mlat=" + latitude + "&mlon=" + longitude + "#map=15/" + latitude +  "/" + longitude;
        System.out.println(url);
        mapsView.getEngine().load(url);*/
        
        URL pathToFile = getClass().getClassLoader().getResource("property-map.html");
        
        WebEngine webEngine = mapsView.getEngine();
        webEngine.load(pathToFile.toExternalForm());
        
        webEngine.executeScript("setLocation()");
    }
}
