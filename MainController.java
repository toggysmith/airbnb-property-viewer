// @TODO: Refactor class

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * MainController hosts FXML GUI elements and onAction methods for the main pane
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
    
    //RangeValues object that stores the constantly updating combo box values so that the combo boxes themselves do not need to be passed around 
    private RangeValues comboBoxRangeValues;
    
    private ContentContainerManager contentContainerManager;
    private MapController mapController;
    private StatController statController;
    
    /**
     * 
     */
    public void setUpPanes() throws IOException
    {
        contentContainerManager = new ContentContainerManager(switchPane);
        
        comboBoxRangeValues = new RangeValues(RangeBoxEnum.NOMIN.toString(), RangeBoxEnum.NOMAX.toString());
        mapController = (MapController) contentContainerManager.getController(MapController.class);
        statController = (StatController) contentContainerManager.getController(StatController.class);
        mapController.createMap();
    }
    
    /*
     * On Action method linked to the next button in the main-pane FXML file, so that when that button is pressed the next pane will be displayed, relative to the currenlty displayed pane
     */
    @FXML
    private void nextPane()
    {
        Pane nextPane = contentContainerManager.getNext();
        switchPane.getChildren().setAll(nextPane);
    }
    
    /*
     * On Action method linked to the previous button in the main-pane FXML file, so that when that button is pressed the previos pane will be displayed, relative to the currenlty displayed pane
     */
    @FXML
    private void prevPane()
    {
        Pane previousPane = contentContainerManager.getPrevious();
        switchPane.getChildren().setAll(previousPane);
    }
    
    /*
     * enables the buttons when a valid price range is selected
     */
    private void enableButtons()
    {
        rightButton.setDisable(false);
        leftButton.setDisable(false);
    }
   
    /*
     * On action method linked to the from combobox in the main-pane FXML file, so that when a new price option is selected it checks whether the price range is valid.
     */
    @FXML
    private void processFromBox()
    {
        checkBoxes(fromRangeBox.getValue(), toRangeBox.getValue());
    }
    
    /*
     * On action method linked to the to combobox in the main-pane FXML file, so that when a new price option is selected it checks whether the price range is valid
     */
    @FXML
    private void processToBox()
    {
        checkBoxes(fromRangeBox.getValue(), toRangeBox.getValue());
    }
    
    /*
     * Method use to check that the user selected price ranges are valid, this method is only executed if both combo boxes have a value selected. The from value needs to be smaller than the to value selected.
     * "No min" means that there is no minimum and "No max" means there is no maximum
     * @param String fromValue, the selected value from the "from combo box"
     * @param String toValue, the selected value from the "to combo box"
     * 
     */
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
                //when the invalid range is displayed then the combo boxes are reverted back to a valid range
                //this is done by 
                fromRangeBox.setValue(comboBoxRangeValues.convertFromIntToStr(comboBoxRangeValues.getFromValue()));
                toRangeBox.setValue(comboBoxRangeValues.convertToIntToStr(comboBoxRangeValues.getToValue()));
            }
            //everytime the comboboxes are changed so are the values storing the combo box values in the mapController and statController
            mapController.updateMap();
            statController.updateValues();
        }
    }
    
    /*
     * A method called when an invalid range is selected and displays an alert to the user.
     */
    private void rangeWarningAlert()
    {
        Alert invalidRange = new Alert(AlertType.WARNING);
        invalidRange.setTitle("Warning");
        invalidRange.setHeaderText("Invalid Range Selected");
        invalidRange.setContentText("The From Price Selected Should Be Lower Than The To Price");
        invalidRange.showAndWait();
    } 
    
    /*
     * returns the object storing the combo box range values
     * @return RangeValues, containing the from value and to value
     */
    public RangeValues getRangeValues()
    {
        return comboBoxRangeValues;
    }
    
    /*
     * returns the combo box object storing the "from values"
     * @return ComboBox<String>, returns a combo box storing strings
     */
    public ComboBox<String> getFromBox()
    {
        return fromRangeBox;
    }
    
    /*
     * returns the combo box object storing the "to values"
     * @return ComboBox<String>, returns a combo box storing strings
     */
    public ComboBox<String> getToBox()
    {
        return toRangeBox;
    }
}