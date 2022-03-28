import javafx.fxml.FXML;
import java.awt.Desktop;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;

/**
 * 
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class WelcomeController extends Controller
{
    private final String GITHUB_REPO_URL = "https://github.kcl.ac.uk/k21064940/airbnb";
    
    /**
     * Called when the user presses the 'Visit our GitHub' button. It opens the project repository on
     * GitHub in the user's default browser.
     */
    @FXML
    public void visitOurGitHubButtonOnClick()
    {
        try
        {
            Desktop.getDesktop().browse(new URL(GITHUB_REPO_URL).toURI());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            final String ERROR_MESSAGE = String.format("Unable to open the GitHub repo at '%s'. " + 
                                                       "There may be no browser on this device " +
                                                       "or the URL may be broken.",
                                                       GITHUB_REPO_URL);
            
            AlertManager.showNonTerminatingError(ERROR_MESSAGE);
        }
    }
    
    /**
     * Called when the user presses the 'Swap color mode' button. It switches the color mode to dark theme
     * if it's already light theme and light theme if it's already dark theme. This simply involves
     * swapping out CSS stylesheets.
     */
    @FXML
    public void swapColorModeOnClick()
    {
        MainView.swapColorMode();
    }
}