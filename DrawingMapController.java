import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import java.net.URL;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class DrawingMapController extends Controller
{
    @FXML WebView browser;

    private boolean isDragging = true;
    private int dragStartX = 0;
    private int dragStartY = 0;
    
    @FXML
    public void initialize()
    {
        WebEngine webEngine = browser.getEngine();
        
        URL pathToFile = getClass().getClassLoader().getResource("resources/drawing-map/index.html");
        
        webEngine.load(pathToFile.toExternalForm());
    }

    @FXML
    public void startDragging(MouseEvent event)
    {
        System.out.println("Started");
        isDragging = true;
        dragStartX = (int) event.getSceneX();
        dragStartY = (int) event.getSceneY();
    }

    @FXML
    public void duringDragging(MouseEvent event)
    {
        if (isDragging)
        {
            int moveAmountX = (int) event.getSceneX() - dragStartX;
            int moveAmountY = (int) event.getSceneY() - dragStartY;
            System.out.println("X: " + moveAmountX + ", Y: " + moveAmountY);
        }
    }

    @FXML
    public void endDragging(MouseEvent event)
    {
        isDragging = false;
    }
}