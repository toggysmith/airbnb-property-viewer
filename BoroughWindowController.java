import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.collections.ObservableList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

/**
 * BoroughWindowController hosts FXML GUI elements.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class BoroughWindowController extends Controller
{
    @FXML private TableView<AirbnbListing> boroughTable;
    @FXML private TableColumn<AirbnbListing, String> nameColumn;
    @FXML private TableColumn<AirbnbListing, String> priceColumn;
    @FXML private TableColumn<AirbnbListing, String> reviewsColumn;
    @FXML private TableColumn<AirbnbListing, String> minNightsColumn;

    @FXML private ComboBox<ComboBoxOrderEnum> orderBox;
    
    @FXML private Label fromPrice;
    @FXML private Label toPrice;
    
    private BoroughWindow boroughWindow;
    private Map<ComboBoxOrderEnum, TableColumn<AirbnbListing, String>> comboBoxOrder;

    @FXML private HBox pieChart;
    @FXML private ComboBox attributeBox;
    
    @FXML private BorderPane mapContainer;
    
    private OpenLayersMap openLayersMap  = new OpenLayersMap("resources/open-layers-map/map.html", 11, -0.115937, 51.511437); // Creates a map with the centre around the stand campus.

    private PieChartView pieView;

    /**
     * Adds the map to the pane that is shown and gives the map the ability to show markers.
     */
    @FXML
    public void initialize()
    {
        mapContainer.setCenter(openLayersMap);

        openLayersMap.addBehaviour(OpenLayersMap.Behaviour.MARKER);
    }
    
    /**
     * Setsup the brorough window, populating the table and the combo box, and assigning the onClick methods.
     * This also adds all the listings given to the map and sets the maps position as the average of those listings.
     * @param listings The listings in the borough in the price range.
     * @param boroughWindow The borough that the window is for.
     */
    public void initialise(ObservableList<AirbnbListing> listings, BoroughWindow boroughWindow)
    {
        this.boroughWindow = boroughWindow;
        populateTable(listings);
        populateOrderBox();
        setOnRowClicked();
        assignSort();
        assignPriceLabels();
        
        setMapPosition(listings);
        addPropertiesToJsFile(listings);
        openLayersMap.executeScript("enableMarkerClicking();", true);
    
        setUpComboBox();
    }
    
    /**
     * Adds the listings to the table and assigns what attributes of those listings go in each column.
     * @param listings The listings that will be displayed in the table.
     */
    protected void populateTable(ObservableList<AirbnbListing> listings)
    {
        boroughTable.setItems(listings);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("host_name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        reviewsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfReviews"));
        minNightsColumn.setCellValueFactory(new PropertyValueFactory<>("minimumNights"));
    }
    
    /**
     * Adds the different orders for th table to the combo box.
     */
    protected void populateOrderBox()
    {
        orderBox.getItems().addAll(getSortBoxOptions());
    }
    
    /**
     * Maps the type of sort to the collumn that it will be sorting.
     */
    private List<ComboBoxOrderEnum> getSortBoxOptions()
    {
        comboBoxOrder = new HashMap<>();
        
        comboBoxOrder.put(ComboBoxOrderEnum.HOST_NAME_ASCENDING, nameColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.HOST_NAME_DESCENDING, nameColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.PRICE_ASCENDING, priceColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.PRICE_DESCENDING, priceColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.NUMBER_OF_REVIEWS_ASCENDING, reviewsColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.NUMBER_OF_REVIEWS_DESCENDING, reviewsColumn);
        
        List<ComboBoxOrderEnum> options = Arrays.asList(ComboBoxOrderEnum.values());
        
        return options;
    }

    /**
     * Sets the row factory for the table to make all rows call tableClicked().
     */
    protected void setOnRowClicked()
    {
        boroughTable.setRowFactory(e -> tableClicked());
    }

    /**
     * Makes all the rows in the table call rowClicked() when they are clicked.
     */
    private TableRow<AirbnbListing> tableClicked()
    {
        TableRow<AirbnbListing> row = new TableRow<>();
        row.setOnMouseClicked(event -> rowClicked(row));
        return row;
    }

    /**
     * Makes the row that has been clicked create a property window for the listing that was being displayed in the row.
     */
    private void rowClicked(TableRow<AirbnbListing> row)
    {
        if (! row.isEmpty()) {
            AirbnbListing listing = row.getItem();
            boroughWindow.createPropertyWindow(listing);
        }
    }
    
    /**
     * Assigns the table to sort when the combo box is clicked.
     */
    protected void assignSort()
    {
        orderBox.setOnAction(e -> sort(orderBox.getValue()));
    }
    
    /**
     * Sorts the table accoring to what is in the combo box.
     */
    private void sort(ComboBoxOrderEnum comboBoxOrderEnum)
    {
        if (comboBoxOrderEnum == null)
        {
            return;    
        }

        TableColumn<AirbnbListing, String> column = comboBoxOrder.get(comboBoxOrderEnum);
        TableView table = boroughTable;
        column.setSortable(true);
        ObservableList<TableColumn> sortBy = table.getSortOrder();
        sortBy.clear();
        sortBy.add(column);
        column.setSortType(comboBoxOrderEnum.getOrder());
        table.sort();
        column.setSortable(false);
    }
    
    /**
     * Assigns the lables with the price range.
     */
    protected void assignPriceLabels()
    {
        fromPrice.setText(String.format(fromPrice.getText(), boroughWindow.getFromPrice()));
        toPrice.setText(String.format(toPrice.getText(), boroughWindow.getToPrice()));
    }
    
    private void makePieChart()
    {
         try
        {
            pieView = new PieChartView();
            AnchorPane chartPane = pieView.setUpPieChart();
            pieChart.getChildren().setAll(chartPane);
            }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    private void setUpPieChart(String selectedAttribute)
    {
        int[] attributeValues = new int[boroughTable.getItems().size()];   
        
        if(selectedAttribute.equals("Price")){
            attributeValues = boroughTable.getItems().stream()
                                                     .mapToInt(listing -> listing.getPrice())
                                                     .toArray();
                                                     
            pieView.populatePieChart(attributeValues,"£");                                         
        }else if(selectedAttribute.equals("Number of reviews")){
            attributeValues = boroughTable.getItems().stream()
                                                     .mapToInt(listing -> listing.getNumberOfReviews())
                                                     .toArray();
        pieView.populatePieChart(attributeValues,"");
        }else if(selectedAttribute.equals("Min number of nights")){
            attributeValues = boroughTable.getItems().stream()
                                                     .mapToInt(listing -> listing.getMinimumNights())
                                                     .toArray();
        pieView.populatePieChart(attributeValues,"");
        }
                                                    
        
        
    }
    
    private void setUpComboBox()
    {
        List<String> comboBoxStrings = new ArrayList<String>();
        comboBoxStrings.add("Price");
        comboBoxStrings.add("Number of reviews");
        comboBoxStrings.add("Min number of nights");
        attributeBox.getItems().addAll(comboBoxStrings);
        
        makePieChart();
    }
    
    @FXML
    public void selectedBox()
    {
            setUpPieChart(attributeBox.getValue().toString());
    }
    
    private void setMapPosition(List<AirbnbListing> listings)
    {
        Position position = ListingManipulator.getAveragePosition(listings);
        double longitude = position.getLongitude();
        double latitude = position.getLatitude();
        openLayersMap.executeScript(String.format("setLongLat(%f, %f)", longitude, latitude), true);
    }
    
    /**
     * Adds the listings in the borough in the price range to the map.
     */
    private void addPropertiesToJsFile(ObservableList<AirbnbListing> listings)
    {
        for (AirbnbListing listing : listings)
        {
            String id = listing.getId();
            double longitude = listing.getLongitude();
            double latitude = listing.getLatitude();
            int price = listing.getPrice();

            String jsObject = String.format("{id: %s, longitude: %f, latitude: %f, price: %d}", id, longitude, latitude, price);
            String jsScript = String.format("addMarker(%s)", jsObject);

            openLayersMap.executeScript(jsScript, true);
        }
    }
}