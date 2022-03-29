import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * DestinationDetailsStage creates the window that can be seen on screen for a given destination listing.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class DestinationDetailsStage extends Stage
{
    /**
     * Create a window and load the FXML file.
     * @param listing The listing that this Stage shows.
     */
    public DestinationDetailsStage(DestinationListing listing) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("destination-window.fxml"));
        
        Pane pane = loader.load();
        DestinationDetailsController destinationWindowController = loader.getController();
        destinationWindowController.setup(listing);
        
        setOnCloseRequest(e -> windowClosed());
        
        Scene scene = new Scene(pane, 1500, 800);
        MainView.addToOpenWindows(scene);
        MainView.setColorMode(scene);
        setScene(scene);
        setTitle(listing.getDestinationName());
        show();
    }
    
    /**
     * Alerts the DestinationDetailsFactory that this window has been closed.
     */
    public void windowClosed()
    {
        DestinationDetailsFactory.getDestinationDetailFactory().destinationDetailClosed(this);
        MainView.removeFromOpenWindows(getScene());
    }
}
