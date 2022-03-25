import javafx.fxml.FXML;
import java.awt.Desktop;
import java.net.URL;

/**
 * Responsible accepting input from the welcome view and converting it into commands for the model or view.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class WelcomeController extends Controller
{
    /**
     * Open up the GitHub repo for the project in the browser.
     */
    @FXML
    public void visitOurGitHubButtonOnClick()
    {
        try
        {
            Desktop.getDesktop().browse(new URL("https://github.kcl.ac.uk/k21064940/airbnb").toURI());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            AlertManager.showNonTerminatingError("Unable to open website.");
        }
    }
    
    /**
     * Swap the color mode from dark to light or light to dark.
     */
    @FXML
    public void swapColorModeOnClick()
    {
        MainView.swapColorMode();
    }
}