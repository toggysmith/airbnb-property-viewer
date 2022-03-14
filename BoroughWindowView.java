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
    private Map<ComboBoxOrderEnum, TableColumn<AirbnbListing, String>> comboBoxOrder;
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

        boroughWindowController.orderBox.getItems().addAll(getSortBoxOptions());
        assignSort(boroughWindowController.orderBox);

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

    private List<ComboBoxOrderEnum> getSortBoxOptions()
    {
        comboBoxOrder = new HashMap<>();
        
        comboBoxOrder.put(ComboBoxOrderEnum.HOST_NAME_ASCENDING, boroughWindowController.nameColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.HOST_NAME_DESCENDING, boroughWindowController.nameColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.PRICE_ASCENDING, boroughWindowController.priceColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.PRICE_DESCENDING, boroughWindowController.priceColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.NUMBER_OF_REVIEWS_ASCENDING, boroughWindowController.reviewsColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.NUMBER_OF_REVIEWS_DESCENDING, boroughWindowController.reviewsColumn);
        
        List<ComboBoxOrderEnum> options = ComboBoxOrderEnum.getAll();
        
        return options;
    }

    private void assignSort(ComboBox box)
    {
        box.setOnAction(e -> sort(box.getValue()));
    }

    private void sort(Object comboBoxOrderObject)
    {
        if (!(comboBoxOrderObject instanceof ComboBoxOrderEnum))
        {
            return;
        }
        ComboBoxOrderEnum comboBoxOrderEnum = (ComboBoxOrderEnum) comboBoxOrderObject;
        if (comboBoxOrder == null)
        {
            return;    
        }

        TableColumn<AirbnbListing, String> column = comboBoxOrder.get(comboBoxOrderEnum);
        TableView table = boroughWindowController.boroughTable;
        column.setSortable(true);
        ObservableList<TableColumn> sortBy = table.getSortOrder();
        sortBy.clear();
        sortBy.add(column);
        column.setSortType(comboBoxOrderEnum.getOrder());
        table.sort();
        column.setSortable(false);
    }

}
