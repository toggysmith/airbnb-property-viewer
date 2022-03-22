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
    private @FXML Button refreshButton;
    
    private OpenLayersMap openLayersMap;
    
    private MapMode mapMode;
    
    private enum MapMode {
        DRAWING_MODE, MARKER_MODE;
    }

    @FXML
    public void initialize()
    {
        openLayersMap = new OpenLayersMap("resources/open-layers-map/map.html");

        borderPane.setCenter(openLayersMap);
        
        mapMode = MapMode.DRAWING_MODE;
        
        // Add the markers
        for (AirbnbListing listing : AirbnbDataLoader.getListings())
        {
            String script = "{id: " + listing.getId() + ", longitude: " + listing.getLongitude() + ", latitude: " + listing.getLatitude() + ", price: " + listing.getPrice() + "}";
            
            openLayersMap.executeScript("addProperty(" + script + ")", true);
        }
    }
    
    @FXML
    public void drawingModeButtonOnAction()
    {
        if (mapMode == MapMode.DRAWING_MODE) return;
        
        refreshButton.setDisable(true);
        
        mapMode = MapMode.DRAWING_MODE;
        openLayersMap.executeScript("switchToDrawingMode()", false);
    }
    
    @FXML
    public void markerModeButtonOnAction()
    {
        if (mapMode == MapMode.MARKER_MODE) return;
        
        refreshButton.setDisable(false);
        
        mapMode = MapMode.MARKER_MODE;
        
        MainController mainController = MainWindow.getMainWindow().getMainController();
        
        int fromPrice = mainController.getRangeValues().getFromValue();
        int toPrice = mainController.getRangeValues().getToValue();
        
        openLayersMap.executeScript("switchToMarkerMode(" + fromPrice + ", " + toPrice + ")", false);
    }
    
    @FXML
    public void refreshButtonOnAction()
    {
        MainController mainController = MainWindow.getMainWindow().getMainController();
        
        int fromPrice = mainController.getRangeValues().getFromValue();
        int toPrice = mainController.getRangeValues().getToValue();
        
        openLayersMap.executeScript("refreshMarkers(" + fromPrice + ", " + toPrice + ")", false);
    }
}