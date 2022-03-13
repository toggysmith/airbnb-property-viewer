import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import java.util.Set;
import javafx.scene.control.Label;
import java.util.List;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.control.Control;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Write a description of class PropertyWindowView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PropertyWindowView extends Stage
{
    private ObservableList<AirbnbListing> listings;
    PropertyWindowController propertyWindowController;
    /**
     * Create a window and load the FXML file.
     */
    public PropertyWindowView(String boroughName) throws Exception
    {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("property-window.fxml"));
        
        Scene scene = new Scene(loader.load());
        
        listings = MainView.getListingsInBorough(boroughName);
        
        propertyWindowController = loader.getController();
        populateTable(propertyWindowController.propertyTable);
        
        setScene(scene);
        setTitle(boroughName);
        show();
    }
    
    public void setListings(ObservableList<AirbnbListing> listings)
    {
        this.listings = listings;
        clearTable(propertyWindowController.propertyTable);
        populateTable(propertyWindowController.propertyTable);
    }
    
    private void clearTable(TableView table)
    {
        table.getRowFactory();
    }
    
    private void populateTable(TableView table)
    {
        table.setItems(listings);
        propertyWindowController.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        propertyWindowController.priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        propertyWindowController.reviewsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfReviews"));
        propertyWindowController.minNightsColumn.setCellValueFactory(new PropertyValueFactory<>("minimumNights"));
    }
    
}
