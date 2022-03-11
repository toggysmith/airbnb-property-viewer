import java.util.ArrayList;
import javafx.scene.layout.Pane;
import java.util.List;

/**
 * A circular list that has no end and no beginning, only the current position.
 *
 * @author Adam Murray K21003575
 * @version 11/03/2022
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
        list = new ArrayList<>();
        currentItem = 0;
    }
    
    /**
     * Gets the next pane from the list.
     * @return The next pane in the list.
     */
    public Pane getNext()
    {
        if (list.size() == 0)
        {
            return null;
        }
        currentItem = (currentItem + 1) % list.size();
        return list.get(currentItem);
    }
    
    /**
     * Gets the previous pane from the list.
     * @return The previous pane in the list.
     */
    public Pane getPrev()
    {
        if (list.size() == 0)
        {
            return null;
        }
        currentItem = list.size() - ((list.size() - currentItem) % list.size()) - 1;
        return list.get(currentItem);
    }
    
    /**
     * Adds a pane to the list.
     * The pane is added so that it will appear after the previously added pane.
     * @param pane The pane to be added.
     */
    public void add(Pane pane)
    {
        list.add(pane);
    }
}
