import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;

/**
 * MainController hosts FXML GUI elements and onAction methods for the main pane.
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

    // RangeValues object that stores the constantly updating combobox values so that the comboboxes themselves do not need to
    // be passed around.
    private RangeValues comboBoxRangeValues;

    private ContentContainerManager contentContainerManager;
    private MapController mapController;
    private StatController statController;

    /**
     * Initialise fields, get controllers and create buttons.
     */
    public void setUpPanes()
    {
        contentContainerManager = new ContentContainerManager(switchPane);
        comboBoxRangeValues = new RangeValues(RangeBoxEnum.NOMIN.toString(), RangeBoxEnum.NOMAX.toString());

        mapController = (MapController) contentContainerManager.getController(MapController.class);
        statController = (StatController) contentContainerManager.getController(StatController.class);

        mapController.createButtons();
    }

    /*
     * On Action method linked to the next button in the main-pane FXML file, so that when that button is pressed the next pane will
     * be displayed, relative to the currently displayed pane.
     */
    @FXML
    private void nextPane()
    {
        Pane nextPane = contentContainerManager.getNext();
        switchPane.getChildren().setAll(nextPane);
    }

    /*
     * On Action method linked to the previous button in the main-pane FXML file, so that when that button is pressed the previous
     * pane will be displayed, relative to the currently displayed pane.
     */
    @FXML
    private void prevPane()
    {
        Pane previousPane = contentContainerManager.getPrevious();
        switchPane.getChildren().setAll(previousPane);
    }

    /*
     * Enables the buttons when a valid price range is selected.
     */
    private void enableButtons()
    {
        rightButton.setDisable(false);
        leftButton.setDisable(false);
    }

    /*
     * On action method linked to the from combobox in the main-pane FXML file, so that when a new price option is selected it checks
     * whether the price range is valid.
     */
    @FXML
    private void processFromBox()
    {
        checkBoxes(fromRangeBox.getValue(), toRangeBox.getValue());
    }

    /*
     * On action method linked to the to combobox in the main-pane FXML file, so that when a new price option is selected it checks
     * whether the price range is valid
     */
    @FXML
    private void processToBox()
    {
        checkBoxes(fromRangeBox.getValue(), toRangeBox.getValue());
    }

    /*
     * Checks that the user selected price ranges are valid. This method is only executed if both comboboxes have a value selected.
     * The from value needs to be smaller than the to value selected.
     * 
     * "No min" means that there is no minimum and "No max" means there is no maximum
     * 
     * @param fromValue The selected value from the "from combo box".
     * @param toValue The selected value from the "to combo box".
     */
    private void checkBoxes(String fromValue, String toValue)
    {
        // Don't continue if either of the arguments are null.
        if (toValue == null || fromValue == null)
        {
            return;
        }

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
            AlertManager.showWarning("The from-price selected should be lower than the to-price.");
            
            fromRangeBox.setValue(comboBoxRangeValues.getFromValueStr());
            toRangeBox.setValue(comboBoxRangeValues.getToValueStr());
        }
        
        // Every time the comboboxes are changed so are the values storing the combo box values in the mapController and
        // statController.
        mapController.updateButtons();
        statController.updateValues();
    }

    /**
     * @return The range values which contain the from-value and to-value.
     */
    public RangeValues getRangeValues()
    {
        return comboBoxRangeValues;
    }

    /**
     * @return The combobox object which stores the from-values.
     */
    public ComboBox<String> getFromBox()
    {
        return fromRangeBox;
    }

    /**
     * @return The combobox object which stores the to-values.
     */
    public ComboBox<String> getToBox()
    {
        return toRangeBox;
    }
}