// @TODO: Refactor class
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import java.net.URL;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Write a description of class PropertyWindowController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PropertyWindowController extends Controller
{
    @FXML private BorderPane root;
    @FXML private Label propertyName;
    @FXML private Label hostName;
    @FXML private Label boroughName;
    @FXML private Label roomType;
    @FXML private Label price;
    @FXML private Label minimumNights;
    @FXML private Label numberOfReviews;
    @FXML private Label availability;

    private OpenLayersMap openLayersMap  = new OpenLayersMap("resources/open-layers-map/map.html", 16, -0.115937, 51.511437);
    private double longitude;
    private double latitude;
    private AirbnbListing listing;

    @FXML
    public void initialize()
    {
        root.setCenter(openLayersMap);

        openLayersMap.addBehaviour(OpenLayersMap.Behaviour.MARKER);
    }

    public void setup(AirbnbListing listing)
    {
        this.listing = listing;
        longitude = listing.getLongitude();
        latitude = listing.getLatitude();

        openLayersMap.executeScript(String.format("setLongLat(%f, %f)", longitude, latitude), true);

        addPropertieToJsFile();
        populateLabels();
    }

    private void addPropertieToJsFile()
    {
        String id = listing.getId();
        double longitude = listing.getLongitude();
        double latitude = listing.getLatitude();
        int price = listing.getPrice();

        String jsObject = String.format("{id: %s, longitude: %f, latitude: %f, price: %d}", id, longitude, latitude, price);
        String jsScript = String.format("addMarker(%s);", jsObject);

        openLayersMap.executeScript(jsScript, true);
    }

    private void populateLabels()
    {
        propertyName.setText(String.format(propertyName.getText(), listing.getName()));
        hostName.setText(String.format(hostName.getText(), listing.getHost_name()));
        boroughName.setText(String.format(boroughName.getText(), listing.getNeighbourhood()));
        roomType.setText(String.format(roomType.getText(), listing.getNeighbourhood()));
        price.setText(String.format(price.getText(), listing.getPrice()));
        minimumNights.setText(String.format(minimumNights.getText(), listing.getMinimumNights()));
        numberOfReviews.setText(String.format(numberOfReviews.getText(), listing.getNumberOfReviews()));
        availability.setText(String.format(availability.getText(), listing.getAvailability365()));
    }
}
