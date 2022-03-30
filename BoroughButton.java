import java.util.List;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

/**
 * Responsible for creating the borough button by creating its component elements (the hexagon, the borough label and the
 * quantity visualiser) and placing them onto the borough map.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class BoroughButton
{
    // Hexagon properties:
    private final static double RADIUS = 60.0;
    private final static double N = Math.sqrt(RADIUS * RADIUS * 0.75);
    private final static double TILE_WIDTH = 2 * N;
    private final static double TILE_HEIGHT = 2 * RADIUS;
    private final static double SEPARATION = 7.5;
    private final static double STARTING_OFFSET = 15;
    private final static int STROKE_WIDTH = 2;
    
    private final static int VBOX_SPACING = 12;
    
    private final List<AirbnbListing> boroughListings;
    
    private QuantityVisualiser quantityVisualiser;
    private List<AirbnbListing> boroughListingsInPriceRange;
    
    /**
     * Create the hexagon, label and quantity visualiser that make up the button and place them onto the borough map.
     * @param borough The borough.
     * @param boroughMap The AnchorPane that represents the borough map.
     */
    public BoroughButton(Borough borough, AnchorPane boroughMap)
    {
        boroughListings = ListingProcessor.filterByBorough(AirbnbDataLoader.getListings(), borough.getName());
        
        double xPosition = STARTING_OFFSET + getRelativeXPosition(borough);
        double yPosition = STARTING_OFFSET + getRelativeYPosition(borough);
        
        // Used to offset every other row of borough buttons:
        if (borough.getY() % 2 == 0)
        {
            xPosition += SEPARATION / 2;
        }
        
        VBox vbox = createVBox(xPosition, yPosition, borough, boroughMap);
        addHexagonToVBox(vbox, xPosition, yPosition, borough, boroughMap);
        
        boroughMap.getChildren().add(vbox);
    }
    
    /**
     * Update the value in the quantity visualiser.
     * @param noOfPropertiesInBoroughWithMost The number of properties in the borough with the most properties.
     */
    public void update(long noOfPropertiesInBoroughWithMost)
    {
        int fromValue = MainWindow.getMainWindow().getMainController().getRangeValues().getFromValue();
        int toValue = MainWindow.getMainWindow().getMainController().getRangeValues().getToValue();
        
        boroughListingsInPriceRange = ListingProcessor.filterByPriceRange(boroughListings, fromValue, toValue);
        
        quantityVisualiser.setCurrentQuantity(boroughListingsInPriceRange.size());
        quantityVisualiser.setRangeUpperBound(noOfPropertiesInBoroughWithMost);
    }
    
    /*
     * Create the VBox.
     */
    private VBox createVBox(double xPosition, double yPosition, Borough borough, AnchorPane boroughMap)
    {
        VBox vbox = new VBox();
        
        Label boroughAbbreviationLabel = new Label(borough.getAbbreviation());
        quantityVisualiser = new QuantityVisualiser();
        
        // Set VBox properties:
        vbox.setSpacing(VBOX_SPACING);
        vbox.setMouseTransparent(true);
        vbox.setPrefSize(TILE_WIDTH, TILE_HEIGHT);
        vbox.setAlignment(Pos.CENTER);
        
        vbox.getChildren().addAll(boroughAbbreviationLabel, quantityVisualiser);
        
        return vbox;
    }
    
    /*
     * Add hexagon to VBox.
     */
    private void addHexagonToVBox(VBox vbox, double xPosition, double yPosition, Borough borough, AnchorPane boroughMap)
    {
        Hexagon hexagon = new Hexagon(xPosition, yPosition, STROKE_WIDTH, RADIUS);
        boroughMap.getChildren().add(hexagon);
        
        // If the hexagon had a bounding box, make the hexagon be in the top left of that box.
        boroughMap.setLeftAnchor(vbox, xPosition);
        boroughMap.setTopAnchor(vbox, yPosition - TILE_HEIGHT * 0.25);
        
        hexagon.setOnMouseClicked(e -> createBoroughWindow(borough));
    }
    
    /*
     * Tries to create a new borough window with the listings in the borough in the price range.
     */ 
    private void createBoroughWindow(Borough borough)
    {
        try
        {
            BoroughDetailsStageFactory.getBoroughDetailsStageFactory().newBoroughWindowWithListings(borough, boroughListingsInPriceRange);
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
    
    /*
     * Calculate the relative x-position of a given borough.
     */
    private double getRelativeXPosition(Borough borough)
    {
        return borough.getX() * (TILE_WIDTH + SEPARATION) + ((borough.getY() + 1) % 2) * N;
    }
    
    /*
     * Calculate the relative y-position of a given borough.
     */
    private double getRelativeYPosition(Borough borough)
    {
        return borough.getY() * (TILE_HEIGHT * 0.75 + SEPARATION) + TILE_HEIGHT * 0.25;
    }
}
