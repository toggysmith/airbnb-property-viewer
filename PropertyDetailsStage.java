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
public class PropertyDetailsStage extends Stage
{
    private AirbnbListing listing;
    
    /**
     * Create a window and load the FXML file.
     */
    public PropertyDetailsStage(AirbnbListing listing) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("property-window.fxml"));
        
        this.listing = listing;
        
        Pane pane = loader.load();
        PropertyDetailsController propertyDetailsController = loader.getController();
        propertyDetailsController.setup(listing);
        
        setOnCloseRequest(e -> windowClosed());
        
        Scene scene = new Scene(pane, 1500, 800);
        MainView.addToOpenWindows(scene);
        MainView.setColorMode(scene);
        setScene(scene);
        setTitle(listing.getName());
        show();
    }
    
    public AirbnbListing getListing() { return listing; }
    
    /**
     * Alerts the PropertyWindowFactory that this window has been closed.
     */
    public void windowClosed()
    {
        PropertyDetailsStageFactory.getPropertyDetailsStageFactory().propertyWindowClosed(this);
        MainView.removeFromOpenWindows(getScene());
    }
}
