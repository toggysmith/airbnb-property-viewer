import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import java.net.URL;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class DrawingMapController extends Controller
{
    @FXML BorderPane borderPane;

    @FXML
    public void initialize()
    {
        OpenLayersMap openLayersMap = new OpenLayersMap("resources/open-layers-map/map.html", 50.0, 0.0);

        borderPane.setCenter(openLayersMap);
    }
    
    
}