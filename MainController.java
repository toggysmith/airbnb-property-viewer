import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;


import java.io.IOException;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;

/**
 * MainController hosts FXML GUI elements and onAction methods.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto   Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public class MainController
{
    @FXML private ComboBox<String> fromRangeBox;
    @FXML private ComboBox<String> toRangeBox;
    @FXML private Pane switchPane;
    @FXML private Button leftButton;
    @FXML private Button rightButton;
    
    private Pane welcomePane;
    private RangeValues comboBoxRangeValues;
    
    private Pane mapPane;
    private CircularList<Pane> windowPanes = new CircularLinkedList<Pane>();
    private MapController mapController;
    private boolean parsing = false;
    public void setUpPanes() throws IOException
    {
     welcomePane = loadPane("welcome-pane.fxml");
     addPaneToWindowPanes(welcomePane);   
     BoroughButton.mainController = this;
     mapPane = loadPane("map-pane.fxml");
     addPaneToWindowPanes(mapPane);
     setSwitchPaneChild(welcomePane); 
       
     comboBoxRangeValues = new RangeValues(RangeBoxEnum.NOMIN.toString(), RangeBoxEnum.NOMAX.toString());
    }
    
    private void addPaneToWindowPanes(Pane newPane)
    {
        windowPanes.add(newPane);
        
        newPane.prefWidthProperty().bind(switchPane.widthProperty());
        newPane.prefHeightProperty().bind(switchPane.heightProperty());
    }
        
    private void setSwitchPaneChild(Pane childPane)
    {
        switchPane.getChildren().setAll(childPane);
    }
    
    private Pane loadPane(String fxmlFileName) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        
        Pane pane = loader.load();
        
        if (fxmlFileName == "map-pane.fxml")
            mapController = loader.getController();
        
        return pane;
    }
    
    @FXML
    private void nextPane()
    {
       Pane nextPane =  windowPanes.getNext();
       setSwitchPaneChild(nextPane);
    }
    
    @FXML
    private void prevPane()
    {
        Pane prevPane = windowPanes.getPrev();
        setSwitchPaneChild(prevPane); 
    }
    
    
    
    
    /**
    private void processRangeValues(String fromValue, String toRange)
    {
        if(fromValue.equals(RangeBoxEnum.NOMIN.toString())){
            comboBoxRangeValues.setFromValue("0");
        }else{
            comboBoxRangeValues.setFromValue(fromValue);                                         
        }
        
        if(toRange.equals(RangeBoxEnum.NOMAX.toString())){
            comboBoxRangeValues.setToValue(Integer.toString(Integer.MAX_VALUE));
        }else{
            comboBoxRangeValues.setToValue(toRange);
        }
    }
    */
   @FXML
    private void checkComboBoxes()
    {
        String selectedFromStr = fromRangeBox.getValue();
        String selectedToStr = toRangeBox.getValue();
        if(selectedFromStr != null && selectedToStr != null && !parsing){
            invalidRangeCheck(selectedFromStr, selectedToStr);
        }
        
        mapController.deleteMap();
        mapController.createMap();
    }
    
   private void retrievePrevRangeValues(int prevFromValue, int prevToValue)
    {
        if(prevFromValue == 0){
            fromRangeBox.setValue(RangeBoxEnum.NOMIN.toString());
        }else{
            fromRangeBox.setValue(Integer.toString(prevFromValue)); 
        }
                   
        if(prevToValue == Integer.MAX_VALUE){
            toRangeBox.setValue(RangeBoxEnum.NOMAX.toString());
        }else{
            toRangeBox.setValue(Integer.toString(prevToValue));
        }
    }
    
   private void invalidRangeCheck(String fromValue, String toValue)
    {
        int currentFromValue = Integer.parseInt(fromValue);
        int currentToValue =  Integer.parseInt(toValue);
        
        int prevFromValue = comboBoxRangeValues.getFromValue();
        int prevToValue = comboBoxRangeValues.getToValue();
        
        comboBoxRangeValues.setFromValue(fromValue);
        comboBoxRangeValues.setToValue(toValue);
            
            if(currentFromValue < currentToValue){
                enableButtons();
            }else{
            parsing = true;
            rangeWarningAlert();
            retrievePrevRangeValues(prevFromValue,prevToValue);
            enableButtons();
            comboBoxRangeValues.setFromValue(Integer.toString(prevFromValue));
            comboBoxRangeValues.setToValue(Integer.toString(prevToValue));    
            }
        parsing = false;
    }
   
    private void enableButtons(){
        rightButton.setDisable(false);
        leftButton.setDisable(false);
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