import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.util.List;

public class WelcomeController extends Controller
{
    private List<Controller> controllers;
    
    public void setControllers(List<Controller> controllers)
    {
        this.controllers = controllers;
    }
    
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