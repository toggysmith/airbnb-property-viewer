import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import java.net.URL;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DrawingMapController extends Controller
{
    private @FXML BorderPane borderPane;
    private @FXML Label statusLabel;
    
    private OpenLayersMap openLayersMap;

    @FXML
    public void initialize()
    {
        openLayersMap = new OpenLayersMap("resources/open-layers-map/map.html");

        borderPane.setCenter(openLayersMap);
        
        // Add the markers
        for (AirbnbListing listing : AirbnbDataLoader.getListings())
        {
            openLayersMap.executeScript("addMarkers(" + listing.getLongitude() + ", " + listing.getLatitude() + ");", true);
        }
    }
    
    @FXML
    public void drawingModeButtonOnAction()
    {
        openLayersMap.executeScript("switchToDrawingMode()", false);
    }
    
    @FXML
    public void markerModeButtonOnAction()
    {
        openLayersMap.executeScript("switchToMarkerMode()", false);
    }
}