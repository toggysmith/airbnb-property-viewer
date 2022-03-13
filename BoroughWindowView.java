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
public class BoroughWindowView extends Stage
{
    private ObservableList<AirbnbListing> listings;
    BoroughWindowController boroughWindowController;
    /**
     * Create a window and load the FXML file.
     */
    public BoroughWindowView(String boroughName) throws Exception
    {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("borough-window.fxml"));
        
        Scene scene = new Scene(loader.load());
        
        listings = MainView.getListingsInBorough(boroughName);
        
        boroughWindowController = loader.getController();
        populateTable(boroughWindowController.boroughTable);
        
        boroughWindowController.sortBox.getItems().addAll(getSortBoxOptions());
        assignSort(boroughWindowController.sortBox);
        
        setScene(scene);
        setTitle(boroughName);
        show();
    }
    
    private void populateTable(TableView table)
    {
        table.setItems(listings);
        boroughWindowController.nameColumn.setCellValueFactory(new PropertyValueFactory<>("host_name"));
        boroughWindowController.priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        boroughWindowController.reviewsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfReviews"));
        boroughWindowController.minNightsColumn.setCellValueFactory(new PropertyValueFactory<>("minimumNights"));
    }
    
    private ArrayList<String> getSortBoxOptions()
    {
        ArrayList<String> options = new ArrayList<>();
        
        options.add("Host Name (Ascending)");
        options.add("Host Name (Descending)");
        options.add("Price (Ascending)");
        options.add("Price (Descending)");
        options.add("Number of reviews (Ascending)");
        options.add("Number of reviews (Descending)");
        
        return options;
    }
    
    private void assignSort(ComboBox box)
    {
        box.setOnMouseClicked(e -> boroughWindowController.boroughTable.sort());
    }
    
}
