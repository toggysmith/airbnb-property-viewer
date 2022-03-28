/**
 * A circular list that has no end and no beginning, only the current position.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public interface CircularList<E>
{
    /**
     * @return The next element in the list.
     */
    public E getNext();
    
    /**
     * @return The previous element in the list.
     */
    public E getPrev();
    
    /**
     * @return The current element in the list.
     */
    public E getCurrent();
    
    /**
     * Adds an element to the list.
     * The element is added so that it will appear after the previously added element.
     * @param element The element to be added.
     */
    public void add(E element);
    
    /**
     * @return The number of elements in the list.
     */
    public int size();
}