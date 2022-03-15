import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.ComboBox;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;

/**
 * BoroughWindowController hosts FXML GUI elements.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public class BoroughWindowController
{
    @FXML public TableView boroughTable;
    @FXML public TableColumn<AirbnbListing, String> nameColumn;
    @FXML public TableColumn<AirbnbListing, String> priceColumn;
    @FXML public TableColumn<AirbnbListing, String> reviewsColumn;
    @FXML public TableColumn<AirbnbListing, String> minNightsColumn;

    @FXML public ComboBox<ComboBoxOrderEnum> orderBox;

    public void setOnRowClicked ()
    {
        boroughTable.setRowFactory(e -> tableClicked());
    }

    private TableRow<AirbnbListing> tableClicked()
    {
        TableRow<AirbnbListing> row = new TableRow<>();
        row.setOnMouseClicked(event -> rowClicked(row));
        return row;
    }

    private void rowClicked(TableRow<AirbnbListing> row)
    {
        if (! row.isEmpty()) {
            AirbnbListing listing = row.getItem();
            try
            {
                new PropertyWindowView(listing);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
