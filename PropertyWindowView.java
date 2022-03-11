import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.scene.Scene;

/**
 * Write a description of class PropertyWindowView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PropertyWindowView extends Stage
{
    
    /**
     * Create a window and load the FXML file.
     */
    public PropertyWindowView(String boroughName) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("property-window.fxml"));
        
        Scene scene = new Scene(loader.load());
        
        PropertyWindowController propertyWindowController = loader.getController();
        
        setScene(scene);
        setTitle(boroughName);
        show();
    }
    
}
