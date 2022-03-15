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
    @FXML public ComboBox fromRangeBox;
    @FXML public ComboBox toRangeBox;
    @FXML public Pane switchPane;
    @FXML public Button leftButton;
    @FXML public Button rightButton;
    
    //welcome window message components
    public Pane welcomePane;    
    @FXML public Text welcomeTitle;
    
    private Pane mapPane;
    public static int fromRangeValue;
    public static int toRangeValue;
    
    private DoublyLinkedList<Pane> windowPanes = new DoublyLinkedList<Pane>();
    private Pane currentPane;
    
    public void setUpPanes() throws IOException
    {
       welcomePane = loadPane( "welcomeWindow.fxml");
       windowPanes.add(welcomePane);
       
       mapPane = loadPane("map-pane.fxml");
       windowPanes.add(mapPane);
       setSwitchPaneChild(welcomePane); 
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
       Pane nextPane =  windowPanes.getNextElement(currentPane);
       setSwitchPaneChild(nextPane);
    }
    
    @FXML
    private void prevPane()
    {
        Pane prevPane = (Pane) windowPanes.getPrevElement(currentPane);
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
    
    private int getMinPrice()
    {
        return AirbnbDataLoader.getListings().stream()
                                             .map(listing -> listing.getPrice())
                                             .min(Integer::compare)
                                             .get();
    }
    
    private int getMaxPrice()
    {
        return AirbnbDataLoader.getListings().stream()
                                             .map(listing -> listing.getPrice())
                                             .max(Integer::compare)
                                             .get();
    }
    
    private void retrievePrevRangeValues(int prevFromValue, int prevToValue)
    {
        if(prevFromValue == getMinPrice()){
            fromRangeBox.setValue(RangeBoxEnum.NOMIN.toString());
        }else{
            fromRangeBox.setValue(Integer.toString(prevFromValue)); 
        }
                   
        if(prevToValue == getMaxPrice()){
            toRangeBox.setValue(RangeBoxEnum.NOMAX.toString());
        }else{
            toRangeBox.setValue(Integer.toString(prevToValue));
        }
    }
    
    private void processRangeValues(String fromValue, String toRange)
    {
        if(fromValue.equals(RangeBoxEnum.NOMIN.toString())){
            fromRangeValue = getMinPrice();
        }else{
                fromRangeValue = Integer.parseInt(fromValue);                                         
        }
        
        if(toRange.equals(RangeBoxEnum.NOMAX.toString())){
            toRangeValue = getMaxPrice();
        }else{
            toRangeValue = Integer.parseInt(toRange);
        }
    }
    
    private boolean invalidRangeCheck()
    {
        boolean isValidRange = true;
        
        
        try{
            String selectedFromStr = (String)fromRangeBox.getValue();
            String selectedToStr = (String) toRangeBox.getValue();    
            if(selectedFromStr != null && selectedToStr != null){
                int prevFromValue = fromRangeValue;
                int prevToValue = toRangeValue;
                
                processRangeValues(selectedFromStr,selectedToStr);
                if(fromRangeValue < toRangeValue){
                   isValidRange = false;
                }else{
                   rangeWarningAlert();
                   retrievePrevRangeValues(prevFromValue,prevToValue);
                }
            }
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
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
}