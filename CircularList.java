/**
 * A circular list that has no end and no beginning, only the current position.
 *
 * @author Adam Murray K21003575
 * @author Augusto Favero K21059800
 * @version 11/03/2022
 */
public interface CircularList<E>
{
    /**
     * Gets the next element from the list.
     * @return The next element in the list.
     */
    public E getNext();
    
    /**
     * Gets the previous element from the list.
     * @return The previous element in the list.
     */
    public E getPrev();
    
    /**
     * Gets the current element from the list.
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
     * Gets the number of elements in the list.
     * @return The number of elements in the list.
     */
    public int size();
}