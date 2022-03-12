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
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.ScrollPane;
import javafx.beans.InvalidationListener;

public class MapController
{
    @FXML private AnchorPane hexagonTileMap;
    @FXML private ScrollPane scrollPane;
    @FXML private Pane root;
    
    private Map<Object, String> boroughNames; 

    private final static double r = 60.0;
    private final static double n = Math.sqrt(r * r * 0.75);
    private final static double TILE_WIDTH = 2 * n;
    private final static double TILE_HEIGHT = 2 * r;
    private final static double SEPARATION = 15;
    private final static int STROKE_WIDTH = 4;

    @FXML
    public void initialize()
    {
        scrollPane.prefWidthProperty().bind(root.widthProperty());
        scrollPane.prefHeightProperty().bind(root.heightProperty());
        
        boroughNames = new HashMap<>();
        createMap();
    }
    
    private void createHexagon(int x, int y)
    {
        double xCoord = Hexagon.STROKE_WIDTH / 2.0 + x * (TILE_WIDTH + SEPARATION) + (y % 2) * n;
        double yCoord = Hexagon.STROKE_WIDTH / 2.0 + y * (TILE_HEIGHT * 0.75 + SEPARATION) + TILE_HEIGHT * 0.25;
        
        if (y % 2 != 0) xCoord += SEPARATION / 2;
        
        VBox vbox = new VBox();
        Label label = new Label("Southwark");
        vbox.getChildren().add(label);
        
        Hexagon hexagon = new Hexagon(xCoord, yCoord);
        hexagonTileMap.getChildren().add(hexagon);
        
        hexagonTileMap.setLeftAnchor(vbox, xCoord);
        hexagonTileMap.setTopAnchor(vbox, yCoord - TILE_HEIGHT * 0.25);
        vbox.setPrefSize(TILE_WIDTH, TILE_HEIGHT);
        vbox.setAlignment(Pos.CENTER);
        hexagonTileMap.getChildren().add(vbox);
        
        vbox.setMouseTransparent(true);
        hexagon.setOnMouseClicked(e -> createBoroughWindow(boroughNames.get(hexagon)));
        
        boroughNames.put(hexagon, label.getText());
    }

    private void createMap()
    {
        createHexagon(0, 0);
        createHexagon(1, 0);
        createHexagon(2, 0);
        createHexagon(0, 1);
        
        for (int i = 0; i < 7; i++)
            createHexagon(i, 3);
    }
    
    private void createBoroughWindow(String windowTitle)
    {
        try
        {
            new PropertyWindowView(windowTitle);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}