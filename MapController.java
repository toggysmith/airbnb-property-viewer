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

public class MapController extends Pane
{
    @FXML private AnchorPane hexagonTileMap;
    
    private Map<Object, String> boroughNames; 

    private final static double r = 60.0;
    private final static double n = Math.sqrt(r * r * 0.75);
    private final static double TILE_WIDTH = 2 * n;
    private final static double TILE_HEIGHT = 2 * r;
    private final static double SEPARATION = 15;

    @FXML
    public void initialize()
    {
        boroughNames = new HashMap<>();
        createMap();
    }

    private void createMap()
    {
        int rowCount = 7;
        int tilesPerRow = 7;
        int xStartOffset = 40;
        int yStartOffset = 40;

        for (int x = 0; x < tilesPerRow; x++)
        {
            for (int y = 0; y < rowCount; y++)
            {
                double xCoord = x * (TILE_WIDTH + SEPARATION) + (y % 2) * n + xStartOffset;
                double yCoord = y * (TILE_HEIGHT * 0.75 + SEPARATION) + yStartOffset;

                if (y % 2 != 0) {xCoord += SEPARATION / 2;}

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

                hexagon.setOnMouseClicked(e -> createBoroughWindow(boroughNames.get(hexagon)));
                
                boroughNames.put(hexagon, label.getText());
            }
        }
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