import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class WelcomePaneView extends Pane
{
    private Label welcomeTitle;
    private Label instructionArea;
    private Label authors;
    private BorderPane welcomePane;
    
    public WelcomePaneView(Double prefSizeX, Double prefSizeY){
        welcomePane = new BorderPane();
        welcomePane.setPrefSize(prefSizeX,prefSizeY);
        
        instructionArea = new Label();
        authors = new Label();
        welcomeTitle = new Label();
        
        welcomeTitle.setText("Welcome");
        instructionArea.setText("user instructions");
        authors.setText("Augusto Toggy Mathew Adam");
        
        welcomePane.setTop(welcomeTitle);
        welcomePane.setCenter(instructionArea);
        welcomePane.setBottom(authors);
    }
    
    public Pane getPane()
    {
        return welcomePane;
    }
}
