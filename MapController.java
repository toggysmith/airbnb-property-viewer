import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import java.awt.event.ActionEvent;
import javafx.scene.control.ScrollPane;
import javafx.application.Platform;

public class MapController extends Pane
{
    @FXML private AnchorPane boroughMap;
    @FXML private ScrollPane scrollPane;
    @FXML private Pane root;

    @FXML
    public void initialize()
    {
        scrollPane.prefWidthProperty().bind(root.widthProperty());
        scrollPane.prefHeightProperty().bind(root.heightProperty());
        
        createMap();
    }

    private void createMap()
    {
        BoroughButton.setBoroughMap(boroughMap);
        
        for (BoroughEnum borough : BoroughEnum.values())
        {
            new BoroughButton(borough);
        }
    }
    
    private void createBoroughWindow(String windowTitle)
    {
        try
        {
            new BoroughWindowView(windowTitle);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}