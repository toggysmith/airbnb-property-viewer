import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import java.net.URL;

public class DrawingMapController extends Controller
{
    @FXML WebView browser;
    
    @FXML
    public void initialize()
    {
        WebEngine webEngine = browser.getEngine();
        
        URL pathToFile = getClass().getClassLoader().getResource("drawing-map.html");
        
        webEngine.load(pathToFile.toExternalForm());
    }
}