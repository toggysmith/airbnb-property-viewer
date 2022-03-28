import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

/**
 * Responsible for terminating the program sensibly when a fatal error is encountered and informing the user of the
 * nature of the error. This provides a better user experience than terminating the program without warning or
 * worse not handling the error at all.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class AlertManager
{
    /**
     * The default error message displayed when an error alert occurs with no given error details.
     */
    public static final String DEFAULT_ERROR_MESSAGE = "Unfortunately an unknown error has occurred.";
    
    /**
     * Displays an error alert on the screen with a description of the error that occurred. The error alert will not
     * cause the program to terminate.
     * @param errorDescription A description of the error that occurred possibly explaining why it happened. If this
     *                         value is null, the default error message is shown instead.
     */
    public static void showNonTerminatingError(String errorDescription)
    {
        Alert alert = new Alert(AlertType.ERROR);

        alert.setTitle("Error");
        alert.setContentText(errorDescription == null ? DEFAULT_ERROR_MESSAGE : errorDescription);
        alert.showAndWait();
    }
    
    /**
     * Displays an error alert on the screen with a description of the error that occurred. The program terminates
     * once the user clicks 'Ok'.
     * @param errorDescription A description of the error that occurred possibly explaining why it happened. If this
     *                         value is null, the default error message is shown instead.
     */
    public static void showTerminatingError(String errorDescription)
    {
        showNonTerminatingError(errorDescription);
        
        System.exit(1); // `System.exit(1)` is used instead of `Platform.exit()` because it allows for a non-zero exit
                        // value to indicate an error occurred during execution, and it is immediate which prevents
                        // code running after this that relies on the failed code.
    }
    
    /**
     * Displays a warning alert on the screen with a description of what caused the warning.
     * @param warningDescription A description of what caused the warning.
     */
    public static void showWarning(String warningDescription)
    {
        if (warningDescription == null)
        {
            showTerminatingError("No warning description provided.");
        }
        
        Alert alert = new Alert(AlertType.WARNING);

        alert.setTitle("Warning");
        alert.setContentText(warningDescription);
        alert.showAndWait();
    }
}
