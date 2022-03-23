// @TODO: Refactor class

import java.util.*;
import java.util.stream.*;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * Write a description of class StatView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatView extends Pane
{
    
    
    
    public StatView() throws Exception{
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stat-pane.fxml"));
        
        Scene scene = new Scene(loader.load());
        
        StatController statController = loader.getController();
        statController.initialize();
    
        // setScene(scene);
        // setTitle("A");
        // show();
    }
    
    
}