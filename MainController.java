import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
/**
 * MainController hosts FXML GUI elements and onAction methods.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public class MainController
{
    @FXML public ComboBox<String> fromRangeBox;
    @FXML public ComboBox<String> toRangeBox;
    @FXML public Pane switchPane;
    @FXML public Button leftButton;
    @FXML public Button rightButton;
    
    //welcome window message components
    public Pane welcomePane;    
    @FXML public Text welcomeTitle;
    
    private RangeValues comboBoxRangeValues;
    
    private Pane mapPane;
    //private int fromRangeValue;
    //private int toRangeValue;
    
    private CircularList<Pane> windowPanes = new CircularLinkedList<Pane>();
    private Pane currentPane;
    
    public void setUpPanes() throws IOException
    {
       welcomePane = loadPane( "welcomeWindow.fxml");
       windowPanes.add(welcomePane);
       
       mapPane = loadPane("map-pane.fxml");
       windowPanes.add(mapPane);
       setSwitchPaneChild(welcomePane); 
       
       comboBoxRangeValues = new RangeValues(RangeBoxEnum.NOMIN.toString(), RangeBoxEnum.NOMAX.toString());
    }
    
    private void setSwitchPaneChild(Pane childPane)
    {
        currentPane = childPane;
        switchPane.getChildren().setAll(childPane);
    }
    
    private Pane loadPane(String fxmlFileName) throws IOException
    {
        return FXMLLoader.load(getClass().getResource(fxmlFileName));
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
     * Method to enable use of the buttons
     */
    @FXML 
    private void enableButtons()
    {
      if(!invalidRangeCheck()){
          rightButton.setDisable(false);
          leftButton.setDisable(false);
      }
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
    
    private boolean invalidRangeCheck()
    {
        boolean isValidRange = true;
        
        //set defualt value to no min and no max
        //object class, that stores two strings 
        
        String selectedFromStr = fromRangeBox.getValue();
        String selectedToStr = toRangeBox.getValue();
        
        if(selectedFromStr != null && selectedToStr != null){
            
            int prevFromValue = comboBoxRangeValues.getFromValue();
            int prevToValue = comboBoxRangeValues.getToValue();
                
            processRangeValues(selectedFromStr,selectedToStr);
            if(comboBoxRangeValues.getFromValue() < comboBoxRangeValues.getToValue()){
            isValidRange = false;
            }else{
            rangeWarningAlert();
            retrievePrevRangeValues(prevFromValue,prevToValue);
            return isValidRange;
            }
        }
        return isValidRange;
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
}