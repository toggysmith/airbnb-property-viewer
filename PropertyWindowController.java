// @TODO: Refactor class
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
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
        //mapsView.getEngine().load("https://www.google.com/maps/@" + listing.getLatitude() +  "," + listing.getLongitude() +  ",12.96z");
        //mapsView.getEngine().load("https://www.google.com/maps/search/?api=1&query=" + listing.getLatitude() +  "," + listing.getLongitude());
        System.out.println("https://www.google.com/maps/@?api=1&map_action=pano&viewpoint=" + listing.getLatitude() +  "," + listing.getLongitude());
        mapsView.getEngine().load("https://www.google.com/maps/@?api=1&map_action=pano&viewpoint=" + listing.getLatitude() +  "," + listing.getLongitude());
    }
}
