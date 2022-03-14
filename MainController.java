import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


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
    @FXML public BorderPane welcomePane;    
    @FXML public TextField welcomeTitle;
    @FXML public Pane mapPane;
    private DoublyLinkedList<Pane> windowPanes = new DoublyLinkedList<Pane>();
    private Pane currentPane;
    public void loadWelcome() throws java.io.IOException
    {
        welcomePane = FXMLLoader.load(getClass().getResource("welcomeWindow.fxml"));
        switchPane.getChildren().setAll(welcomePane);
        windowPanes.add(welcomePane);
        currentPane = welcomePane;
    }
    
    public void setUpPanes() throws java.io.IOException
    {
       loadWelcome();
       mapPane = FXMLLoader.load(getClass().getResource("map-pane.fxml"));
       windowPanes.add(mapPane);
    }
    
    @FXML
    private void nextPane()
    {
       Pane nextPane =  windowPanes.getNextElement(currentPane);
       currentPane = nextPane;
       switchPane.getChildren().setAll(nextPane); 
    }
    
    @FXML
    private void prevPane()
    {
        Pane prevPane =  windowPanes.getPrevElement(currentPane);
        currentPane = prevPane;
        switchPane.getChildren().setAll(prevPane); 
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
    
    @FXML
    private boolean invalidRangeCheck()
    {   
        try{
        String selectedFromStr = (String)fromRangeBox.getValue();
        String selectedToStr = (String) toRangeBox.getValue();    
        if(selectedFromStr != null & selectedToStr != null){
            int selectedFromInt = Integer.parseInt(selectedFromStr);
            int selectedToInt = Integer.parseInt(selectedToStr);
            
            if(selectedFromInt < selectedToInt){
               return false;
            }else{
               rangeWarningAlert();
               return true;
            }
        }
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        return true;
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