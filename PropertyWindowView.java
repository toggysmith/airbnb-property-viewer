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
public class PropertyWindowView extends Stage
{
    private PropertyWindow propertyWindow;
    
    /**
     * Create a window and load the FXML file.
     */
    public PropertyWindowView(AirbnbListing listing, PropertyWindow propertyWindow) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("property-window.fxml"));
        
        Pane pane = loader.load();
        PropertyWindowController propertyWindowController = loader.getController();
        propertyWindowController.setup(listing);
        
        this.propertyWindow = propertyWindow;
        setOnCloseRequest(e -> propertyWindow.windowClosed());
        
        setScene(new Scene(pane, 1500, 800));
        setTitle(listing.getName());
        show();
    }
}
