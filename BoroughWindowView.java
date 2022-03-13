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
import javafx.scene.control.TableColumn;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import javafx.scene.Node;

/**
 * Write a description of class PropertyWindowView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoroughWindowView extends Stage
{
    private ObservableList<AirbnbListing> listings;
    private BoroughWindowController boroughWindowController;
    private Map<String, SortConditions> comboBoxSortToTabelmap;
    /**
     * Create a window and load the FXML file.
     */
    public BoroughWindowView(String boroughName) throws Exception
    {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("borough-window.fxml"));
        
        Scene scene = new Scene(loader.load());
        
        comboBoxSortToTabelmap = new HashMap<>();
        
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
        comboBoxSortToTabelmap.put("Host Name (Ascending)", new SortConditions(boroughWindowController.nameColumn, TableColumn.SortType.ASCENDING));
        options.add("Host Name (Descending)");
        comboBoxSortToTabelmap.put("Host Name (Descending)", new SortConditions(boroughWindowController.nameColumn, TableColumn.SortType.DESCENDING));
        options.add("Price (Ascending)");
        comboBoxSortToTabelmap.put("Price (Ascending)", new SortConditions(boroughWindowController.priceColumn, TableColumn.SortType.ASCENDING));
        options.add("Price (Descending)");
        comboBoxSortToTabelmap.put("Price (Descending)", new SortConditions(boroughWindowController.priceColumn, TableColumn.SortType.DESCENDING));
        options.add("Number of reviews (Ascending)");
        comboBoxSortToTabelmap.put("Number of reviews (Ascending)", new SortConditions(boroughWindowController.reviewsColumn, TableColumn.SortType.ASCENDING));
        options.add("Number of reviews (Descending)");
        comboBoxSortToTabelmap.put("Number of reviews (Descending)", new SortConditions(boroughWindowController.reviewsColumn, TableColumn.SortType.DESCENDING));

        return options;
    }
    
    private void assignSort(ComboBox box)
    {
        box.setOnMouseClicked(e -> getSort(box));
    }
    
    private void getSort(ComboBox box)
    {
        sort(comboBoxSortToTabelmap.get(box.getValue()));
    }
    
    private void sort(SortConditions sortCondtions)
    {
        if (sortCondtions == null)
        {
            return;    
        }
        
        TableColumn<AirbnbListing, String> column = sortCondtions.getColumn();
        TableView table = boroughWindowController.boroughTable;
        column.setSortable(true);
        ObservableList<TableColumn> sortBy = table.getSortOrder();
        sortBy.clear();
        sortBy.add(column);
        column.setSortType(sortCondtions.getOrder());
        table.sort();
        column.setSortable(false);
    }
    
}
