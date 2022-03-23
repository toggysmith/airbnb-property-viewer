import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Write a description of class StatNode here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class stat extends Pane
{
    private Pane wrapPane;
    private Label title;
    private Label value;
    
    public stat(Pane wrapPane, Label title, Label value, String titleText, String valueText)
    {
    this.wrapPane = wrapPane;
    this.title = title;
    title.setText(titleText);
    this.value = value;
    value.setText(valueText);
    
    wrapPane.getChildren().setAll(value);
    }

    public String getTitle()
    {
    return title.getText();
    }
}

