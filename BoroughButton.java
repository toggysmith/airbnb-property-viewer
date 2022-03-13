import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Pos;

public class BoroughButton
{
    private Hexagon hexagon;
    private VBox vbox;
    private Label label;
    private String boroughAbbreviation, boroughFullName;
    
    private final static double r = 60.0;
    private final static double n = Math.sqrt(r * r * 0.75);
    private final static double TILE_WIDTH = 2 * n;
    private final static double TILE_HEIGHT = 2 * r;
    private final static double SEPARATION = 15;
    
    private static AnchorPane boroughMap;
    
    public static void setBoroughMap(AnchorPane boroughMap)
    {
        BoroughButton.boroughMap = boroughMap;
    }

    public BoroughButton(int x, int y, String boroughAbbreviation, String boroughFullName)
    {
        this.boroughAbbreviation = boroughAbbreviation;
        this.boroughFullName = boroughFullName;
        
        double xCoord = x * (TILE_WIDTH + SEPARATION) + ((y+1) % 2) * n;
        double yCoord = y * (TILE_HEIGHT * 0.75 + SEPARATION) + TILE_HEIGHT * 0.25;

        if ((y+1) % 2 != 0) {xCoord += SEPARATION / 2;}

        VBox vbox = new VBox();
        Label label = new Label(boroughAbbreviation);
        vbox.getChildren().add(label);
        
        Hexagon hexagon = new Hexagon(xCoord, yCoord);
        boroughMap.getChildren().add(hexagon);

        boroughMap.setLeftAnchor(vbox, xCoord);
        boroughMap.setTopAnchor(vbox, yCoord - TILE_HEIGHT * 0.25);
        vbox.setPrefSize(TILE_WIDTH, TILE_HEIGHT);
        vbox.setAlignment(Pos.CENTER);
        boroughMap.getChildren().add(vbox);
                
        vbox.setMouseTransparent(true);
        hexagon.setOnMouseClicked(e -> createBoroughWindow(boroughFullName));
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