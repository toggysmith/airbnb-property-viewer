import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import java.util.stream.Collectors;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableRow;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.Arrays;
/**
 * InteractiveStatController controls the interactive statistics where the user can find the closest destinations depending on the selected borough and property name as a reference point
 * 
 * It populates the comboboxes with the boroughs and properties from the price range selected in the main pane combo boxes, allowing the user to find the five closest pubs or tourist
 * attractions to the selected porperty. 
 * 
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version (v1)
 */
public class InteractiveStatController extends Controller
{
    @FXML public HBox comboBoxContainer;
    
    @FXML public ComboBox<String> boroughs;
    @FXML public ComboBox<String> propertyName;
    @FXML public ComboBox<String> price;
    
    @FXML public TableView<InteractiveStatsTableValues> locationsResult;
    @FXML private TableColumn<InteractiveStatsTableValues, String> nameColumn;
    @FXML private TableColumn<InteractiveStatsTableValues, String> addressColumn;
    @FXML private TableColumn<InteractiveStatsTableValues, String> distanceColumn;
    
    private ArrayList<DestinationListing> destinations;
    private ArrayList<DistanceDestinationPair> fiveClosestDestinations;
    private DestinationDistances desCalculator;
    private ArrayList<AirbnbListing> filteredListing;
    
    private DestinationType desType;
    
    /**
     * Creates the table.
     */
    @FXML
    public void initialize()
    {
        createTable();
        setOnRowClicked();
    }
    
    /**
     * Assigns what fields will go in which row.
     */
    private void createTable()
    {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
    }
    
    /**
     * Defines what happens when a row is clicked in the row factory
     */
    protected void setOnRowClicked()
    {
        locationsResult.setRowFactory(e -> tableClicked());
    }

    /**
     * Defines what happens when a row is clicked.
     */
    private TableRow<InteractiveStatsTableValues> tableClicked()
    {
        TableRow<InteractiveStatsTableValues> row = new TableRow<>();
        row.setOnMouseClicked(event -> rowClicked(row));
        return row;
    }

    /**
     * Creates a destination window for the destination in the row clicked.
     */
    private void rowClicked(TableRow<InteractiveStatsTableValues> row)
    {
        if (! row.isEmpty()) {
            InteractiveStatsTableValues listing = row.getItem();
            DestinationWindowFactory.getDestinationWindowFactory().newDestinationWindow(listing.getDistanceDestinationPair().getDestination());
        }
    }
    
    public void updateBoxes(List<AirbnbListing> filteredListing, List<DestinationListing> typesDestinations, DestinationType desType)
    {
        this.desType = desType;
        createNewComboBoxes();
        locationsResult.getItems().clear();
        //reset boxes first to be sure
        this.filteredListing = new ArrayList<>(filteredListing);
        destinations = new ArrayList<>(typesDestinations);
        
        List<String> boroughsList = filteredListing.stream()
                                                   .map(listing -> listing.getNeighbourhood())
                                                   .distinct()
                                                   .collect(Collectors.toList());
        boroughs.getItems().addAll(boroughsList);
        if(desType.equals(DestinationType.PUB)){
            boroughs.setPromptText("Select Borough Name:");
            propertyName.setPromptText("Select Property:");
            price.setPromptText("Pub Price Range");
            ArrayList<String> categories = new ArrayList<>(Arrays.asList("£", "££", "£££"));
            price.getItems().addAll(categories);

        }else if(desType.equals(DestinationType.ATTRACTION)){
            boroughs.setPromptText("Select Borough Name:");
            propertyName.setPromptText("Select Property:");
            price.setPromptText("Ticket Price");
            ArrayList<String> tickets = new ArrayList<>(Arrays.asList("free", "£2.50 - £5.00","£5.00 - £7.00", "£7.00 - £9.00"));
            price.getItems().addAll(tickets);
        }

        propertyName.setDisable(true);
        price.setDisable(true);
    }
    
    /**
     * Creates the new combo boxes.
     */
    private void createNewComboBoxes()
    {
        createNewBoroughComboBox();
        createNewPropertyComboBox();
        createNewPriceComboBox();
    }
    
    /**
     * Creates a new Combo box for the borough.
     */
    private void createNewBoroughComboBox()
    {
        comboBoxContainer.getChildren().remove(boroughs);
        boroughs = new ComboBox<String>();
        boroughs.setOnAction(e -> processBoroughsBox());
        boroughs.setPrefWidth(150);
        comboBoxContainer.getChildren().add(0, boroughs);
    }
    
    /**
     * Creates a new Combo box for the property.
     */
    private void createNewPropertyComboBox()
    {
        comboBoxContainer.getChildren().remove(propertyName);
        propertyName = new ComboBox<String>();
        propertyName.setOnAction(e -> processPropertiesBox());
        propertyName.setPrefWidth(150);
        comboBoxContainer.getChildren().add(1, propertyName);
    }

    /**
     * Creates a new Combo box for the price.
     */
    private void createNewPriceComboBox()
    {
        comboBoxContainer.getChildren().remove(price);
        price = new ComboBox<String>();
        price.setOnAction(e -> processPriceBox());
        price.setPrefWidth(150);
        comboBoxContainer.getChildren().add(2, price);
    }

    @FXML
    private void processBoroughsBox()
    {
       if(boroughs.getValue() != null){
           List<String> properties = filteredListing.stream()
                                                    .filter(listing -> boroughs.getValue().equals(listing.getNeighbourhood()))
                                                    .map(listing -> listing.getName())
                                                    .distinct()
                                                    .collect(Collectors.toList());
                                                                                          
          createNewPropertyComboBox();
          propertyName.setPromptText("Select Property");
          propertyName.getItems().addAll(properties);
          propertyName.setDisable(false);
          createNewPriceComboBox();
          price.setDisable(true);
          if(desType.equals(DestinationType.PUB)){
            price.setPromptText("Pub Price Range");
            ArrayList<String> categories = new ArrayList<>(Arrays.asList("£", "££", "£££"));
            price.getItems().addAll(categories);

        }else if(desType.equals(DestinationType.ATTRACTION)){
            price.setPromptText("Ticket Price");
            ArrayList<String> tickets = new ArrayList<>(Arrays.asList("free", "£2.50 - £5.00","£5.00 - £7.00", "£7.00 - £9.00"));
            price.getItems().addAll(tickets);
        }
        locationsResult.getItems().clear();
          checkBoxes(boroughs.getValue(), propertyName.getValue(), price.getValue());
       }
    }
    
    @FXML 
    private void processPropertiesBox()
    {
        if(propertyName.getValue() != null){
            price.setDisable(false);
            checkBoxes(boroughs.getValue(), propertyName.getValue(), price.getValue());
        }
    }
    
    @FXML 
    private void processPriceBox()
    {
        checkBoxes(boroughs.getValue(), propertyName.getValue(), price.getValue()); 
    }
    
    private void checkBoxes(String boroughSelected, String propertySelected,String priceSelected)
    {
        if(boroughSelected != null && propertySelected != null && priceSelected != null){
            //check array list is empty, or if its smaller then 5 return those 5
           ArrayList<DestinationListing> filteredDestinations = destinations.stream()
                       .filter(destination -> boroughSelected.equals(destination.getBorough()))
                       .filter(destination -> priceSelected.equals(destination.getPrice()))
                       .collect(Collectors.toCollection(ArrayList::new));
                       
           AirbnbListing selectedProperty = filteredListing.stream()
                                                           .filter(listing -> propertySelected.equals(listing.getName()) && boroughSelected.equals(listing.getNeighbourhood()))
                                                           .findFirst().orElse(null);
        
           
           desCalculator = new DestinationDistances();
           desCalculator.addDestinations(filteredDestinations, selectedProperty);
           fiveClosestDestinations = new ArrayList<DistanceDestinationPair>();
           fiveClosestDestinations = desCalculator.getFiveSmallest();
           displayResult();
        }
    }
    
    /**
     * Displays the 5 closest destinations in a table
     */
    private void displayResult()
    {
        locationsResult.getItems().clear();
        for(DistanceDestinationPair eachDestination: fiveClosestDestinations){
            InteractiveStatsTableValues value = new InteractiveStatsTableValues(eachDestination);
            locationsResult.getItems().add(value);
        } 
    }
    
    /**
     * Resets the comboxes and the table using the values already stored.
     */
    @FXML
    public void updateBoxesButtonPressed()
    {
        updateBoxes(filteredListing, destinations, desType);
    }
} 

