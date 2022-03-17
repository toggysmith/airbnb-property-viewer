import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.function.Function;
import java.util.Collections;

/**
 * 
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public class BoroughButton
{
    private Hexagon hexagon;
    private VBox vbox;
    private Label label;
    private QuantityVisualiser quantityVisualiser;
    private String boroughAbbreviation, boroughFullName;

    private final static double r = 60.0;
    private final static double n = Math.sqrt(r * r * 0.75);
    private final static double TILE_WIDTH = 2 * n;
    private final static double TILE_HEIGHT = 2 * r;
    private final static double SEPARATION = 7.5;
    private final static double STARTING_OFFSET = 15;

    private static AnchorPane boroughMap;
    
    private List<AirbnbListing> boroughListings;

    public static void setBoroughMap(AnchorPane boroughMap)
    {
        BoroughButton.boroughMap = boroughMap;
    }

    public BoroughButton(Borough borough)
    {
        this.boroughAbbreviation = borough.ABBREVIATION;
        this.boroughFullName = borough.NAME;
        
        boroughListings = ListingManipulator.filterByBorough(AirbnbDataLoader.getListings(), boroughFullName);

        double xCoord = borough.X * (TILE_WIDTH + SEPARATION) + ((borough.Y + 1) % 2) * n;
        double yCoord = borough.Y * (TILE_HEIGHT * 0.75 + SEPARATION) + TILE_HEIGHT * 0.25;
        
        xCoord += STARTING_OFFSET;
        yCoord += STARTING_OFFSET;

        if ((borough.Y + 1) % 2 != 0) {xCoord += SEPARATION / 2;}

        VBox vbox = new VBox();
        Label label = new Label(boroughAbbreviation);
        
        quantityVisualiser = new QuantityVisualiser();
        
        vbox.getChildren().addAll(label, quantityVisualiser);
        vbox.setSpacing(12);

        Hexagon hexagon = new Hexagon(xCoord, yCoord);
        boroughMap.getChildren().add(hexagon);

        boroughMap.setLeftAnchor(vbox, xCoord);
        boroughMap.setTopAnchor(vbox, yCoord - TILE_HEIGHT * 0.25);
        vbox.setPrefSize(TILE_WIDTH, TILE_HEIGHT);
        vbox.setAlignment(Pos.CENTER);
        boroughMap.getChildren().add(vbox);

        vbox.setMouseTransparent(true);
        hexagon.setOnMouseClicked(e -> createBoroughWindow(borough));
    }
    
    public void update(long noOfPropertiesInBoroughWithMost)
    {
        int fromValue = MainWindow.getMainWindow().getMainController().getRangeValues().getFromValue();
        int toValue = MainWindow.getMainWindow().getMainController().getRangeValues().getToValue();
        
        List<AirbnbListing> boroughListingsInPriceRange = ListingManipulator.filterByPriceRange(boroughListings, fromValue, toValue);
        
        quantityVisualiser.setCurrentQuantity(boroughListingsInPriceRange.size());
        quantityVisualiser.setRangeUpperBound(noOfPropertiesInBoroughWithMost);
    }

    private void createBoroughWindow(Borough borough)
    {
        BoroughWindowFactory.getBoroughWindowFactory().newBoroughWindowWithListings(borough, boroughListings);
    }
}
