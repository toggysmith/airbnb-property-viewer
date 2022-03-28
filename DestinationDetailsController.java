import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

/**
 * DestinationWindowController hosts FXML GUI elements.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class DestinationDetailsController extends Controller
{
    @FXML private BorderPane mapContainer;
    
    @FXML private Label destinationName;
    @FXML private Label address;
    @FXML private Label boroughName;
    
    private OpenLayersMap openLayersMap  = new OpenLayersMap("resources/open-layers-map/map.html", 16, -0.115937, 51.511437); // Creates a map with the centre around the stand campus.
    
    private double longitude;
    private double latitude;
    private DestinationListing listing;
    
    /**
     * The method is called automatically when the fxml file is loaded.
     * It adds the OpenLayersMap to the window and gives it the ability to display markers.
     */
    @FXML
    public void initialize()
    {
        mapContainer.setCenter(openLayersMap);

        openLayersMap.addBehaviour(OpenLayersMap.Behaviour.MARKER);
    }

    /**
     * This method sets up the window for the destination listing that this window is for.
     * @param listing The DestinationListing for the destination.
     */
    public void setup(DestinationListing listing)
    {
        this.listing = listing;
        longitude = listing.getLongitude();
        latitude = listing.getLatitude();

        openLayersMap.executeScript(String.format("setLongLat(%f, %f)", longitude, latitude), true);
        addDestinationToJsFile();
        populateLabels();
    }
    
    /*
     * Gives the Js file this destinations info so that a marker can be placed on the map at its location.
     */ 
    private void addDestinationToJsFile()
    {
        String id = "111111";

        String jsObject = String.format("{id: %s, longitude: %f, latitude: %f}", id, longitude, latitude);
        String jsScript = String.format("addMarker(%s);", jsObject);

        openLayersMap.executeScript(jsScript, true);
    }
    
    /*
     * Adds the destination details to the labels provided.
     */ 
    private void populateLabels()
    {
        destinationName.setText(String.format(destinationName.getText(), listing.getDestinationName()));
        address.setText(String.format(address.getText(), listing.getAddress()));
        boroughName.setText(String.format(boroughName.getText(), listing.getBorough()));
    }
}
