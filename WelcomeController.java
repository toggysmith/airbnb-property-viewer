import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import java.awt.*;
import java.net.URL;
import java.util.List;

public class WelcomeController implements Controller
{
    private List<Controller> controllers;
    
    public void setControllers(List<Controller> controllers)
    {
        this.controllers = controllers;
    }
    
    @FXML
    public void visitOurGitHubButtonOnClick(ActionEvent actionEvent)
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
    
    @FXML
    public void swapColorModeOnClick(ActionEvent actionEvent)
    {
        MainView.swapColorMode();
    }
}