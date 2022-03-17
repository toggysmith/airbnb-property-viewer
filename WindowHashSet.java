import java.util.HashSet;

/**
 * Write a description of class WindowHashSet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WindowHashSet<E> extends HashSet<E>
{
    public E getElementInSet(E elementTest)
    {
        for (E element : this)
        {
            if (element.equals(elementTest))
            {
                return element;
            }
        }        
        return null;
    }
}
