import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Responsible for launching the main window.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class App extends Application
{
    /**
     * Create the main window and maximise it.
     * 
     * @param stage The JavaFX stage.
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        MainWindow mainWindow = MainWindow.getMainWindow();
        
        mainWindow.getMainView().setMaximized(true);
    }
}