// @TODO: Refactor class

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Write a description of class PropertyWindowView here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
        
        setScene(new Scene(pane));
        setTitle(listing.getHost_name());
        show();
    }
}
