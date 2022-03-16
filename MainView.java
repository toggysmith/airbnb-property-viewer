import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javafx.scene.layout.Pane;
import java.util.Arrays;


/**
 * MainView creates the primary application window by loading it from
 * an FXML file. It also uses the AirbnbDataLoader to load the Airbnb
 * listings from the database.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public class MainView extends Stage
{
    private final static String WINDOW_TITLE = "Airbnb Property Viewer";

    private MainWindow mainWindow;
    private MainController mainController;
    
    /**
     * Create the main application window.
     */
    public MainView(MainWindow mainWindow) throws Exception
    {
        // Load the contents of the FXML file into the scene.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        
        this.mainWindow = mainWindow;
        
        Scene scene = new Scene(loader.load());
        
        mainController = loader.getController();

        // Perform basic setup work.
        mainController.setUpPanes();
        setRangeBoxOptions();
        setScene(scene);
        setTitle(WINDOW_TITLE);
        sizeToScene();
        show();
    }

    /**
     * Set the options shown in the range combo boxes.
     */
    private void setRangeBoxOptions()
    {
        mainController.getFromBox().getItems().addAll(generatePriceOptions(RangeBoxEnum.NOMIN.toString()));
        mainController.getToBox().getItems().addAll(generatePriceOptions(RangeBoxEnum.NOMAX.toString()));
    }
    
    /**
     * Generate a list of possible price options.
     * @param noOptionString The string to show to represent none of the other options, e.g. "No min".
     * @return The list of possible price options.
     */
    public List<String> generatePriceOptions(String noOptionString)
    {
        List<String> options = new ArrayList<>();
        
        int stepAmount = 10;
        
        for (int currentPrice = 0; currentPrice <= mainWindow.getMaxPropertyPrice(); currentPrice += stepAmount)
        {
            if (currentPrice == stepAmount * 10) stepAmount = stepAmount * 10;
            
            options.add(currentPrice == 0 ? noOptionString : Integer.toString(currentPrice));
        }
        
        return options;
    }
    
    public MainController getMainController()
    {
        return mainController;
    }
    
}