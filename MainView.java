// @TODO: Refactor class

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;

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
    
    private static boolean isInLightMode = false;
    private static Scene scene;
    private static String lightModeStylesheet;
    private static String darkModeStylesheet;
    
    /**
     * Create the main application window.
     */
    public MainView(MainWindow mainWindow) throws Exception
    {
        // Get the paths to the stylesheets
        lightModeStylesheet = getClass().getClassLoader().getResource("light-mode.css").toExternalForm();
        darkModeStylesheet = getClass().getClassLoader().getResource("dark-mode.css").toExternalForm();
        
        // Load the contents of the FXML file into the scene.
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        
        this.mainWindow = mainWindow;
        
        scene = new Scene(loader.load(), 870, 870);
        
        swapColorMode();
        
        mainController = loader.getController();

        // Perform basic setup work.
        mainController.setUpPanes();
        setRangeBoxOptions();
        setScene(scene);
        setTitle(WINDOW_TITLE);
        sizeToScene();
        setOnHidden(e -> Platform.exit());
        show();
    }
    
    /**
     * Swap color mode.
     */
    public static void swapColorMode()
    {
        isInLightMode = !isInLightMode;
        
        if (isInLightMode)
        {
            scene.getStylesheets().remove(darkModeStylesheet);
            scene.getStylesheets().add(lightModeStylesheet);
        }
        else
        {
            scene.getStylesheets().remove(lightModeStylesheet);
            scene.getStylesheets().add(darkModeStylesheet);
        }
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
        
        for (int currentPrice = 0; currentPrice <= ListingManipulator.getMaxPropertyPrice(); currentPrice += stepAmount)
        {
            if (currentPrice == stepAmount * 10) stepAmount = stepAmount * 10;
            
            options.add(currentPrice == 0 ? noOptionString : Integer.toString(currentPrice));
        }
        
        return options;
    }
    
    /**
     * @return The main controller.
     */
    public MainController getMainController()
    {
        return mainController;
    }
    
    
}