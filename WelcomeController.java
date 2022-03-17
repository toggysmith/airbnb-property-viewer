import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class WelcomeController
{
    @FXML
    public void visitOurGitHubButtonOnClick(ActionEvent actionEvent)
    {
        App.openWebsite("https://github.kcl.ac.uk/k21064940/airbnb");
    }
    
    @FXML
    public void swapColorModeOnClick(ActionEvent actionEvent)
    {
        MainView.swapColorMode();
    }
}