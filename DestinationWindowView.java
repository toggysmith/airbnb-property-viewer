import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * PropertyWindowView creates the window that can be seen on screen for a given property listing.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class DestinationWindowView extends Stage
{
    private DestinationWindow destinationWindow;
    
    /**
     * Create a window and load the FXML file.
     */
    public DestinationWindowView(DestinationListing listing, DestinationWindow destinationWindow) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("destination-window.fxml"));
        
        Pane pane = loader.load();
        DestinationWindowController destinationWindowController = loader.getController();
        destinationWindowController.setup(listing);
        
        this.destinationWindow = destinationWindow;
        setOnCloseRequest(e -> destinationWindow.windowClosed());
        
        setScene(new Scene(pane, 1500, 800));
        setTitle(listing.getDestinationName());
        show();
    }
}
