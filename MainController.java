// @TODO: Refactor class

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * MainController hosts FXML GUI elements and onAction methods.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class MainController extends Controller
{
    @FXML private ComboBox<String> fromRangeBox;
    @FXML private ComboBox<String> toRangeBox;
    @FXML private Pane switchPane;
    @FXML private Button leftButton;
    @FXML private Button rightButton;
    
    private RangeValues comboBoxRangeValues;
    
    private ContentContainerManager contentContainerManager;
    private MapController mapController;
    
    public void setUpPanes() throws IOException
    {
        contentContainerManager = new ContentContainerManager(switchPane);
        
        comboBoxRangeValues = new RangeValues(RangeBoxEnum.NOMIN.toString(), RangeBoxEnum.NOMAX.toString());
     
        mapController = (MapController) ContentContainerManager.getController(MapController.class);
        mapController.createMap();
    }
    
    @FXML
    private void nextPane()
    {
        Pane nextPane = contentContainerManager.getNext();
        switchPane.getChildren().setAll(nextPane);
    }
    
    @FXML
    private void prevPane()
    {
        Pane previousPane = contentContainerManager.getPrevious();
        switchPane.getChildren().setAll(previousPane);
    }
    
    private void enableButtons()
    {
        rightButton.setDisable(false);
        leftButton.setDisable(false);
    }
   
    @FXML
    private void processFromBox()
    {
        checkBoxes(fromRangeBox.getValue(), toRangeBox.getValue());
    }
    
    @FXML
    private void processToBox()
    {
        checkBoxes(fromRangeBox.getValue(), toRangeBox.getValue());
    }
    
    private void checkBoxes(String fromValue, String toValue)
    {
        if (toValue != null && fromValue != null)
        {
            int fromValueInt = comboBoxRangeValues.convertFromStrToInt(fromValue);
            int toValueInt = comboBoxRangeValues.convertToStrToInt(toValue);
            
            if (fromValueInt <= toValueInt)
            {
                comboBoxRangeValues.setFromValue(fromValue);
                comboBoxRangeValues.setToValue(toValue);
                enableButtons();
            }
            else
            {
                rangeWarningAlert();
                fromRangeBox.setValue(comboBoxRangeValues.convertFromIntToStr(comboBoxRangeValues.getFromValue()));
                toRangeBox.setValue(comboBoxRangeValues.convertToIntToStr(comboBoxRangeValues.getToValue()));
            }
            
            mapController.updateMap();
        }
    }
    
    private void rangeWarningAlert()
    {
        Alert invalidRange = new Alert(AlertType.WARNING);
        invalidRange.setTitle("Warning");
        invalidRange.setHeaderText("Invalid Range Selected");
        invalidRange.setContentText("The From Price Selected Should Be Lower Than The To Price");
        invalidRange.showAndWait();
    } 
    
    public RangeValues getRangeValues()
    {
        return comboBoxRangeValues;
    }
    
    public ComboBox<String> getFromBox()
    {
        return fromRangeBox;
    }
    
    public ComboBox<String> getToBox()
    {
        return toRangeBox;
    }
}