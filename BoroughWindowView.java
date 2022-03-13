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

    private ArrayList<ComboBoxSorts> getSortBoxOptions()
    {
        ArrayList<ComboBoxSorts> options = new ArrayList<>();

        options.add(new ComboBoxSorts("Host Name (Ascending)", boroughWindowController.nameColumn, TableColumn.SortType.ASCENDING));
        options.add(new ComboBoxSorts("Host Name (Descending)", boroughWindowController.nameColumn, TableColumn.SortType.DESCENDING));
        options.add(new ComboBoxSorts("Price (Ascending)", boroughWindowController.priceColumn, TableColumn.SortType.ASCENDING));
        options.add(new ComboBoxSorts("Price (Descending)", boroughWindowController.priceColumn, TableColumn.SortType.DESCENDING));
        options.add(new ComboBoxSorts("Number of reviews (Ascending)", boroughWindowController.reviewsColumn, TableColumn.SortType.ASCENDING));
        options.add(new ComboBoxSorts("Number of reviews (Descending)", boroughWindowController.reviewsColumn, TableColumn.SortType.DESCENDING));

        return options;
    }

    private void assignSort(ComboBox box)
    {
        box.setOnAction(e -> sort(box.getValue()));
    }

    private void sort(Object comboBoxSortsObject)
    {
        if (!(comboBoxSortsObject instanceof ComboBoxSorts))
        {
            return;
        }
        ComboBoxSorts comboBoxSorts = (ComboBoxSorts) comboBoxSortsObject;
        if (comboBoxSorts == null)
        {
            return;    
        }

        TableColumn<AirbnbListing, String> column = comboBoxSorts.getColumn();
        TableView table = boroughWindowController.boroughTable;
        column.setSortable(true);
        ObservableList<TableColumn> sortBy = table.getSortOrder();
        sortBy.clear();
        sortBy.add(column);
        column.setSortType(comboBoxSorts.getOrder());
        table.sort();
        column.setSortable(false);
    }

}
