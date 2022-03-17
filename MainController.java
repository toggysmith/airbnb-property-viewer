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
    public void setUpPanes() throws IOException
    {
     welcomePane = loadPane("welcome-pane.fxml");
     addPaneToWindowPanes(welcomePane);
     mapPane = loadPane("map-pane.fxml");
     addPaneToWindowPanes(mapPane);
     setSwitchPaneChild(welcomePane); 
     
     
     comboBoxRangeValues = new RangeValues(RangeBoxEnum.NOMIN.toString(), RangeBoxEnum.NOMAX.toString());
     
           mapController.createMap();
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
   
    private void enableButtons(){
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
       if(toValue != null && fromValue != null){
           int fromValueInt = comboBoxRangeValues.convertFromStrToInt(fromValue);
           int toValueInt = comboBoxRangeValues.convertToStrToInt(toValue);
           
           if(fromValueInt <= toValueInt){
               comboBoxRangeValues.setFromValue(fromValue);
               comboBoxRangeValues.setToValue(toValue);
               enableButtons();
           }else{
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