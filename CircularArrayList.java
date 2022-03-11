import java.util.ArrayList;
import java.util.List;

/**
 * A circular list that has no end and no beginning, only the current position.
 *
 * @author Adam Murray K21003575
 * @version 11/03/2022
 */
public class CircularArrayList<E> implements CircularList<E>
{
    private List<E> list;
    private int currentItem;

    /**
     * Constructor for objects of class CircularArrayList
     */
    public CircularArrayList()
    {
        list = new ArrayList<>();
        currentItem = 0;
    }
    
    /**
     * Gets the next element from the list.
     * @return The next element in the list.
     */
    public E getNext()
    {
        if (list.size() == 0)
        {
            return null;
        }
        currentItem = (currentItem + 1) % list.size();
        return list.get(currentItem);
    }
    
    /**
     * Gets the previous element from the list.
     * @return The previous element in the list.
     */
    public E getPrev()
    {
        if (list.size() == 0)
        {
            return null;
        }
        currentItem = list.size() - ((list.size() - currentItem) % list.size()) - 1;
        return list.get(currentItem);
    }
    
    /**
     * Adds an element to the list.
     * The element is added so that it will appear after the previously added element.
     * @param element The element to be added.
     */
    public void add(E element)
    {
        list.add(element);
    }
}
