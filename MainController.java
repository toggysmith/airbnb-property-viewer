import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * Create the initial window.
 * 
 * @author Tony Smith (K21064940)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Adam Murray (K21003575)
 * @version 1.0
 */
public class MainController extends Application
{
    /**
     * Create the initial window by loading the FXML file.
     * 
     * @param stage The JavaFX stage.
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Airbnb Property Viewer");
        stage.show();
    }
}