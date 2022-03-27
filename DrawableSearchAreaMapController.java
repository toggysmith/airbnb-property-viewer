import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import java.net.URL;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.application.Platform;

/**
 * Responsible for setting up the map that lets the user draw a search area for properties. This map is primarily implemented
 * in HTML/CSS/JS with the help of the OpenLayers library.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class DrawableSearchAreaMapController extends Controller
{
    @FXML private BorderPane borderPane;
    @FXML private CheckBox boroughBoundariesCheckBox;
    @FXML private Button refreshButton;
    
    private OpenLayersMap openLayersMap = new OpenLayersMap("resources/open-layers-map/map.html", 10, -0.115937, 51.511437);
    private enum MapMode { DRAWING_MODE, MARKER_MODE; }
    private MapMode mapMode = MapMode.DRAWING_MODE;



    /**
     * Add the OpenLayers map to the scene and load data about the properties into the JS file.
     */
    @FXML
    public void initialize()
    {
        borderPane.setCenter(openLayersMap);
        
        openLayersMap.addBehaviour(OpenLayersMap.Behaviour.DRAWING, OpenLayersMap.Behaviour.MARKER);

        addPropertiesToJsFile();
        
        openLayersMap.executeScript("enableMarkerClicking();", true);
    }
    
    /**
     * Toggle whether or not the borough boundaries are shown.
     */
    @FXML
    public void boroughBoundariesCheckBoxOnAction()
    {
        String jsScript = String.format("setBoroughBoundariesVisibility(%b);", boroughBoundariesCheckBox.isSelected());
        openLayersMap.executeScript(jsScript, false);
    }

    /**
     * Switch to drawing mode and make sure the refresh button is disabled.
     */
    @FXML
    public void drawingModeButtonOnAction()
    {
        if (mapMode == MapMode.DRAWING_MODE) return;
        
        refreshButton.setDisable(true);
        
        mapMode = MapMode.DRAWING_MODE;
        
        openLayersMap.executeScript("switchToDrawingMode()", false);
    }

    /**
     * Switch to marker mode and make sure the refresh button is enabled.
     */
    @FXML
    public void markerModeButtonOnAction()
    {
        if (mapMode == MapMode.MARKER_MODE) return;
        
        refreshButton.setDisable(false);
        
        mapMode = MapMode.MARKER_MODE;
        
        MainController mainController = MainWindow.getMainWindow().getMainController();
        int fromPrice = mainController.getRangeValues().getFromValue();
        int toPrice = mainController.getRangeValues().getToValue();

        String jsScript = String.format("switchToMarkerMode(%d, %d)", fromPrice, toPrice);
        openLayersMap.executeScript(jsScript, false);
    }

    /**
     * Refresh the markers by clearing them and replacing them with up-to-date ones that match the
     * current price range and area.
     */
    @FXML
    public void refreshButtonOnAction()
    {
        MainController mainController = MainWindow.getMainWindow().getMainController();
        int fromPrice = mainController.getRangeValues().getFromValue();
        int toPrice = mainController.getRangeValues().getToValue();
        
        String jsScript = String.format("refreshMarkers(%d, %d)", fromPrice, toPrice);
        openLayersMap.executeScript(jsScript, false);
    }

    /**
     * Gives the Js file all the listings that the propgram has.
     */
    private void addPropertiesToJsFile()
    {
        for (AirbnbListing listing : AirbnbDataLoader.getListings())
        {
            String id = listing.getId();
            double longitude = listing.getLongitude();
            double latitude = listing.getLatitude();
            int price = listing.getPrice();

            String jsObject = String.format("{id: %s, longitude: %f, latitude: %f, price: %d}", id, longitude, latitude, price);
            String jsScript = String.format("addProperty(%s)", jsObject);

            openLayersMap.executeScript(jsScript, true);
        }
    }
}