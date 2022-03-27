import java.util.HashSet;

/**
 * This is a HashSet with the added ability that if you give
 * it an object it will return to you the object in the set
 * that is is the same according to the equals method
 * of the object.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */ 
public class WindowHashSet<E> extends HashSet<E>
{
    /**
     * This method takes in an object and returns the equivalent object in the
     * set according to the equals method of the objects. Returns null if
     * no such object is found.
     * @param elementTest The object that you want to find the equivalent of.
     * @return The object in the set that is the same as elementTest. 
     * Returns null if no such object is found.
     */
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
