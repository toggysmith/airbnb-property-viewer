import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import java.net.URL;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableRow;

/**
 * PropertyWindowController controls the window created for a given property.
 * This class populates the elements of the window and controls the users interaction
 * with the window.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class PropertyWindowController extends Controller
{
    @FXML private BorderPane root;
    
    @FXML private BorderPane mapContainer;

    
    @FXML private TableView otherPropertiesTable;
    @FXML private TableColumn<AirbnbListing, String> nameColumn;
    @FXML private TableColumn<AirbnbListing, String> priceColumn;
    @FXML private TableColumn<AirbnbListing, String> reviewsColumn;
    @FXML private TableColumn<AirbnbListing, String> minNightsColumn;
    @FXML private TableColumn<AirbnbListing, String> boroughColumn;

    
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
     * This method sets up the window for the property listing that this window is for.
     * @param listing The AirbnbListing for the property.
     */
    public void setup(AirbnbListing listing)
    {
        this.listing = listing;
        longitude = listing.getLongitude();
        latitude = listing.getLatitude();

        openLayersMap.executeScript(String.format("setLongLat(%f, %f)", longitude, latitude), true);
        addPropertyToJsFile();
        
        populateTable(FXCollections.observableArrayList(ListingManipulator.getOtherListingsWithHostId(listing)));
        populateLabels();
        setOnRowClicked();
    }
    
    // This populates the table view with the given properties of the AirbnbListings given.
    // This table is intended to display the other properties owned by the host that owns the property displayed in the window.
    protected void populateTable(ObservableList<AirbnbListing> listings)
    {
        otherPropertiesTable.setPlaceholder(new Label("No other listings from this host found"));
        otherPropertiesTable.setItems(listings);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("host_name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        reviewsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfReviews"));
        minNightsColumn.setCellValueFactory(new PropertyValueFactory<>("minimumNights"));
        boroughColumn.setCellValueFactory(new PropertyValueFactory<>("neighbourhood"));
    }

    // Gives the Js file this properties info so that a marker can be placed on the map at its location.
    private void addPropertyToJsFile()
    {
        String id = listing.getId();

        String jsObject = String.format("{id: %s, longitude: %f, latitude: %f}", id, longitude, latitude);
        String jsScript = String.format("addMarker(%s);", jsObject);

        openLayersMap.executeScript(jsScript, true);
    }

    //Adds the property details to the labels provided.
    private void populateLabels()
    {
        propertyName.setText(String.format(propertyName.getText(), listing.getName()));
        hostName.setText(String.format(hostName.getText(), listing.getHost_name()));
        boroughName.setText(String.format(boroughName.getText(), listing.getNeighbourhood()));
        roomType.setText(String.format(roomType.getText(), listing.getRoom_type()));
        price.setText(String.format(price.getText(), listing.getPrice()));
        minimumNights.setText(String.format(minimumNights.getText(), listing.getMinimumNights()));
        numberOfReviews.setText(String.format(numberOfReviews.getText(), listing.getNumberOfReviews()));
        availability.setText(String.format(availability.getText(), listing.getAvailability365()));
    }
    
    //Opens the row factory for the table view so that we can control what happens when the tabel is clicked.
    protected void setOnRowClicked()
    {
        otherPropertiesTable.setRowFactory(e -> tableClicked());
    }

    // Sets a mouse clicked event for the in the table row.
    private TableRow<AirbnbListing> tableClicked()
    {
        TableRow<AirbnbListing> row = new TableRow<>();
        row.setOnMouseClicked(event -> rowClicked(row));
        return row;
    }

    // Tells the table to try and create a new window for the listing in the table that was clicked.
    private void rowClicked(TableRow<AirbnbListing> row)
    {
        if (! row.isEmpty()) {
            AirbnbListing otherListing = row.getItem();
            PropertyWindowFactory.getPropertyWindowFactory().newPropertyWindow(otherListing);
        }
    }
}
