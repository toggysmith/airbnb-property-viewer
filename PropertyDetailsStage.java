import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * PropertyDetailsStage creates the window that can be seen on screen for a given property listing.
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
     * @param listing The AirbnbListing that this Stage is for.
     */
    public PropertyDetailsStage(AirbnbListing listing) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("property-window.fxml"));
        
        this.listing = listing;
        
        Pane pane = loader.load();
        PropertyDetailsController propertyDetailsController = loader.getController();
        propertyDetailsController.setup(listing);
        
        setOnCloseRequest(e -> stageClosed());
        
        Scene scene = new Scene(pane, 1500, 800);
        MainView.addToOpenWindows(scene);
        MainView.setColorMode(scene);
        setScene(scene);
        setTitle(listing.getName());
        show();
    }
    
    /**
     * @return The AirbnbListing.
     */
    public AirbnbListing getListing() { return listing; }
    
    /**
     * Alerts the PropertyDetailsStageFactory that this window has been closed.
     */
    public void stageClosed()
    {
        PropertyDetailsStageFactory.getPropertyDetailsStageFactory().propertyStageClosed(this);
        MainView.removeFromOpenWindows(getScene());
    }
}
