import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * This creates a new window for displaying info about 
 * the properties in a borough in the price range selected.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */ 
public class BoroughWindowView extends Stage
{
    /**
     * Create a window and load the FXML file.
     */
    public BoroughWindowView(String boroughName, ObservableList<AirbnbListing> listings, BoroughWindow boroughWindow) throws Exception
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("borough-window.fxml"));

        Scene scene = new Scene(loader.load());

        BoroughWindowController boroughWindowController = loader.getController();
        boroughWindowController.initialise(listings, boroughWindow);
                        
        setOnCloseRequest(e -> boroughWindow.windowClosed());
        
        setScene(scene);
        setTitle(boroughName);
        show();
    }

}
