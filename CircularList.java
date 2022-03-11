import java.util.ArrayList;
import javafx.scene.layout.Pane;
import java.util.List;

/**
 * Write a description of class CircularList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CircularList
{
    private List<Pane> list;
    private int currentItem;

    /**
     * Constructor for objects of class CircularList
     */
    public CircularList()
    {
        currentItem = 0;
    }
    
    public Pane getNext()
    {
        currentItem = (currentItem + 1) % list.size() - 1;
        return list.get(currentItem);
    }
    
    public Pane getPrev()
    {
        currentItem = (currentItem - 1);
        if (currentItem < 0)
        {
            currentItem = list.size() - 1;
        }
        return list.get(currentItem);
    }
    
    public void add(Pane pane)
    {
        list.add(pane);
    }
}
