import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

// Project


/**
 * Write a description of class PropertyWindowView here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
        boroughWindowController.Initialise(listings, boroughWindow);
                        
        setOnCloseRequest(e -> boroughWindow.windowClosed());
        
        setScene(scene);
        setTitle(boroughName);
        show();
    }

}
