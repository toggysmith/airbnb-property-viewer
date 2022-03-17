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
import java.util.List;
import java.util.ArrayList;

public class MapController extends Pane
{
    @FXML private AnchorPane boroughMap;
    @FXML private ScrollPane scrollPane;
    @FXML private Pane root;
    
    private List<BoroughButton> buttons;

    @FXML
    public void initialize()
    {
        buttons = new ArrayList<>();
        scrollPane.prefWidthProperty().bind(root.widthProperty());
        scrollPane.prefHeightProperty().bind(root.heightProperty());
    }

    public void createMap()
    {
        BoroughButton.setBoroughMap(boroughMap);
        
        for (Borough borough : Borough.values())
        {
            buttons.add(new BoroughButton(borough));
        }
    }
    
    public void updateMap()
    {
        for (BoroughButton button : buttons)
        {
            button.update();
        }
    }
    
    public Pane getMapPane()
    {
        return root;
    }
}