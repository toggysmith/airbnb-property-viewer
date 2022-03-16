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
    private final static double SEPARATION = 15;

    private static AnchorPane boroughMap;

    public static void setBoroughMap(AnchorPane boroughMap)
    {
        BoroughButton.boroughMap = boroughMap;
    }

    public BoroughButton(Borough borough)
    {
        this.boroughAbbreviation = borough.ABBREVIATION;
        this.boroughFullName = borough.NAME;

        double xCoord = borough.X * (TILE_WIDTH + SEPARATION) + ((borough.Y + 1) % 2) * n;
        double yCoord = borough.Y * (TILE_HEIGHT * 0.75 + SEPARATION) + TILE_HEIGHT * 0.25;

        if ((borough.Y + 1) % 2 != 0) {xCoord += SEPARATION / 2;}

        VBox vbox = new VBox();
        Label label = new Label(boroughAbbreviation);
        try
        {
            quantityVisualiser = new QuantityVisualiser(getNoOfPropertiesInThisBorough(),
                                                        getMinNoOfPropertiesInAnyBorough(),
                                                        getMaxNoOfPropertiesInAnyBorough());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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

    private void createBoroughWindow(Borough borough)
    {
        new BoroughWindow(borough);
    }

    private long getNoOfPropertiesInThisBorough()
    {
        List<AirbnbListing> listings = AirbnbDataLoader.getListings();

        return listings.stream()
                       .filter(listing -> listing.getNeighbourhood().equals(boroughFullName))
                       .count();
    }

    private long getMinNoOfPropertiesInAnyBorough()
    {
        List<AirbnbListing> listings = AirbnbDataLoader.getListings();

        Map<String, Long> mins = listings.stream()
                                         .collect(Collectors.groupingBy(AirbnbListing::getNeighbourhood, Collectors.counting()));
        
        return Collections.min(mins.values());
    }
        
    private long getMaxNoOfPropertiesInAnyBorough()
    {
        List<AirbnbListing> listings = AirbnbDataLoader.getListings();

        Map<String, Long> maxs = listings.stream()
                                         .collect(Collectors.groupingBy(AirbnbListing::getNeighbourhood, Collectors.counting()));
        
        return Collections.max(maxs.values());
    }
}
