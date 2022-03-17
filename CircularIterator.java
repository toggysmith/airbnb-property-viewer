import java.util.ListIterator;
import java.util.List;

/**
 * A circular iterator.
 */
public class CircularIterator<T>
{
    private List<T> list;
    private int currentIndex;
    
    public CircularIterator(List<T> list)
    {
        this.list = list;
        this.currentIndex = 0;
    }
    
    public T previous()
    {
        currentIndex--;
        if (currentIndex < 0) currentIndex = list.size() - 1;
        return list.get(currentIndex);
    }
    
    public T next()
    {
        currentIndex++;
        if (currentIndex >= list.size()) currentIndex = 0;
        return list.get(currentIndex);
    }
}