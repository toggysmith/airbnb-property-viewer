import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.ComboBox;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ChoiceBox;

/**
 * MainController hosts FXML GUI elements and onAction methods.
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
    
    @FXML public ComboBox<ComboBoxOrder> orderBox;
    
    public TableColumn<AirbnbListing, String> getColumn(ComboBoxOrder order)
    {
        if (order == ComboBoxOrder.HOST_NAME_ASCENDING || order == ComboBoxOrder.HOST_NAME_DECENDING)
        {
            return nameColumn;
        }
        else if (order == ComboBoxOrder.PRICE_ASCENDING || order == ComboBoxOrder.PRICE_DECENDING)
        {
            return priceColumn;
        }
        else if (order == ComboBoxOrder.NUMBER_OF_REVIEWS_ASCENDING || order == ComboBoxOrder.NUMBER_OF_REVIEWS_DECENDING)
        {
            return reviewsColumn;
        }
        else
        {
            return null;
        }
    }
}
