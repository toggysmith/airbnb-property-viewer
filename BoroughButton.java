import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class BoroughButton
{
    private final static double r = 60.0;
    private final static double n = Math.sqrt(r * r * 0.75);
    private final static double TILE_WIDTH = 2 * n;
    private final static double TILE_HEIGHT = 2 * r;
    private final static double SEPARATION = 7.5;
    private final static double STARTING_OFFSET = 15;
    
    private final QuantityVisualiser quantityVisualiser;
    private final List<AirbnbListing> boroughListings;
    
    private List<AirbnbListing> boroughListingsInPriceRange;
    
    public BoroughButton(Borough borough, AnchorPane boroughMap)
    {
        boroughListings = ListingProcessor.filterByBorough(AirbnbDataLoader.getListings(), borough.getName());
        
        double xCoord = borough.getX() * (TILE_WIDTH + SEPARATION) + ((borough.getY() + 1) % 2) * n;
        double yCoord = borough.getY() * (TILE_HEIGHT * 0.75 + SEPARATION) + TILE_HEIGHT * 0.25;
        
        xCoord += STARTING_OFFSET;
        yCoord += STARTING_OFFSET;
        
        if ((borough.getY() + 1) % 2 != 0) {xCoord += SEPARATION / 2;}
        
        VBox vbox = new VBox();
        Label label = new Label(borough.getAbbreviation());
        
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
        
        boroughListingsInPriceRange = ListingProcessor.filterByPriceRange(boroughListings, fromValue, toValue);
        
        quantityVisualiser.setCurrentQuantity(boroughListingsInPriceRange.size());
        quantityVisualiser.setRangeUpperBound(noOfPropertiesInBoroughWithMost);
    }
    
    /*
     * Trys to create a new borough window with the listings in the borough in the price range.
     */ 
    private void createBoroughWindow(Borough borough)
    {
        try
        {
            BoroughWindowFactory.getBoroughWindowFactory().newBoroughWindowWithListings(borough, boroughListingsInPriceRange);
        }
        catch (EmptyListException ele)
        {
            emptyBoroughWarningAlert(borough.getName(), ele);
        }
    }
    
    /*
     * Creates a warning alert when a borough with no properties in the price range is clicked.
     */ 
    private void emptyBoroughWarningAlert(String boroughName, EmptyListException ele)
    {
        AlertManager.showWarning(String.format(ele.toString(), boroughName));
    } 
}
