import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import java.util.stream.Collectors;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.util.List;
import java.util.ArrayList;


/**
 * Write a description of class InteractiveStatController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class InteractiveStatController
{
    @FXML public Label Title;
    @FXML public Label First;
    @FXML public Label Second;
    @FXML public Label Third;
    
    @FXML public ComboBox<String> boroughs;
    @FXML public ComboBox<String> propertyName;
    @FXML public ComboBox<String> price;
    
    private ArrayList<DestinationListing> destinations;
    
    private SmallestDistance distanceCalculator;
    
    public void setUpBoxes(List<String> boroughsList, List<String> propertiesList, List<String> pricesList,ArrayList<DestinationListing> typesDestinations)
    {
        boroughs.getItems().addAll(boroughsList);
        propertyName.getItems().addAll(propertiesList);
        price.getItems().addAll(pricesList);
        
        destinations = new ArrayList<>(typesDestinations);
    }
    
    
    @FXML
    private void processBoroughsBox()
    {
       checkBoxes(boroughs.getValue(), propertyName.getValue(), price.getValue());              
    }
    
    @FXML 
    private void processPropertiesBox()
    {
        checkBoxes(boroughs.getValue(), propertyName.getValue(), price.getValue()); 
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
                       .filter(destination -> propertySelected.equals(destination.getPrice()))
                       .collect(Collectors.toCollection(ArrayList::new));
                       
           AirbnbListing selectedProperty = AirbnbDataLoader.getListings().stream()
                                          .filter(listing -> propertySelected.equals(listing.getName()) && boroughSelected.equals(listing.getNeighbourhood()))
                                          .findFirst().orElse(null);
                                          
                                          
           for(DestinationListing eachDestination: filteredDestinations){
               distanceCalculator.addSmallest(eachDestination, selectedProperty);
           }
        }
    }
} 

