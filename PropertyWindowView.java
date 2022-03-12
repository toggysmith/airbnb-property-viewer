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

/**
 * Write a description of class PropertyWindowView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PropertyWindowView extends Stage
{
    private List<AirbnbListing> listings;
    private List<Sort> sorts;
    /**
     * Create a window and load the FXML file.
     */
    public PropertyWindowView(String boroughName) throws Exception
    {
        sorts = createSorts();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("property-window.fxml"));
        
        Scene scene = new Scene(loader.load());
        
        listings = MainView.getListingsInBorough(boroughName);
        
        PropertyWindowController propertyWindowController = loader.getController();
        populateGrid(propertyWindowController.propertyGrid);
        ComboBox comboBox = propertyWindowController.dropdownMenu;
        
        for(Sort sort : sorts) {
            Label item = new Label(sort.getName());
            item.setOnMouseClicked(e -> propertyWindowController.sort(sort, listings));
            comboBox.getItems().add(item);
        }
        
        setScene(scene);
        setTitle(boroughName);
        show();
    }
    
    private void populateGrid(GridPane grid)
    {
        int i = 1;
        for (AirbnbListing listing : listings)
        {
            grid.addRow(i, new Label(listing.getHost_name()), new Label("" + listing.getPrice()), 
                        new Label("" + listing.getNumberOfReviews()), new Label("" + listing.getMinimumNights()));
            i++;
        }
    }
    
    private List<Sort> createSorts()
    {
        List<Sort> sortList = new ArrayList<>();
        sortList.add(new Sort("sort"));

        return sortList;
    }
    
}
