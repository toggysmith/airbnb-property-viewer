import javafx.application.Application;
import javafx.stage.Stage;

/**
 * App is the entry point of the program.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public class App extends Application
{
    public static App instance;
    
    /**
     * Create the MainView which starts the application window.
     * @param stage The JavaFX stage.
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        MainWindow.getMainWindow();
        
        instance = this;
    }
    
    /**
     * Open the website at a specified URL.
     * @param URL The URL of the website.
     */
    public static void openWebsite(String URL)
    {
        if (instance != null)
        {
            instance.getHostServices().showDocument(URL);
        }
    }
}