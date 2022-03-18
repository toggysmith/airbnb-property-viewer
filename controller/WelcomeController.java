package controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.util.List;

// Project
import model.*;
import view.*;
import controller.*;

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
        App.openWebsite("https://github.kcl.ac.uk/k21064940/airbnb");
    }
    
    @FXML
    public void swapColorModeOnClick(ActionEvent actionEvent)
    {
        MainView.swapColorMode();
    }
}