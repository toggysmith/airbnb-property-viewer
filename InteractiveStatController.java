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

import java.util.Arrays;
/**
 * Write a description of class InteractiveStatController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class InteractiveStatController extends Controller
{
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
    
    @FXML
    public void initialize()
    {
        createTable();
        setOnRowClicked();
    }
    
    private void createTable()
    {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
    }
    
    protected void setOnRowClicked()
    {
        locationsResult.setRowFactory(e -> tableClicked());
    }

    private TableRow<InteractiveStatsTableValues> tableClicked()
    {
        TableRow<InteractiveStatsTableValues> row = new TableRow<>();
        row.setOnMouseClicked(event -> rowClicked(row));
        return row;
    }

    private void rowClicked(TableRow<InteractiveStatsTableValues> row)
    {
        if (! row.isEmpty()) {
            InteractiveStatsTableValues listing = row.getItem();
            DestinationWindowFactory.getDestinationWindowFactory().newDestinationWindow(listing.getDistanceDestinationPair().getDestination());
        }
    }
    
    public void updateBoxes(List<AirbnbListing> filteredListing,List<DestinationListing> typesDestinations, DestinationType desType)
    {
        boroughs.getItems().clear();
        price.getItems().clear();
        propertyName.getItems().clear();
        locationsResult.getItems().clear();
        //reset boxes first to be sure
        this.filteredListing = new ArrayList<>(filteredListing);
        destinations = new ArrayList<>(typesDestinations);
        
        List<String> boroughsList = filteredListing.stream()
                                                   .map(listing -> listing.getNeighbourhood())
                                                   .distinct()
                                                   .collect(Collectors.toList());
        boroughs.getItems().addAll(boroughsList);
        //propertyName.getItems().addAll(propertiesList);
        //price.getItems().addAll(pricesList);
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
    
    
    @FXML
    private void processBoroughsBox()
    {
       //checkBoxes(boroughs.getValue(), propertyName.getValue(), price.getValue());
       if(boroughs.getValue() != null){
           List<String> properties = filteredListing.stream()
                                                    .filter(listing -> boroughs.getValue().equals(listing.getNeighbourhood()))
                                                    .map(listing -> listing.getName())
                                                    .distinct()
                                                    .collect(Collectors.toList());
          propertyName.getItems().clear();
          propertyName.setPromptText("Select Property");
          propertyName.getItems().addAll(properties);
          propertyName.setDisable(false);
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
    
    private void displayResult()
    {
        locationsResult.getItems().clear();
        for(DistanceDestinationPair eachDestination: fiveClosestDestinations){
            InteractiveStatsTableValues value = new InteractiveStatsTableValues(eachDestination);
            locationsResult.getItems().add(value);
        } 
    }
} 

