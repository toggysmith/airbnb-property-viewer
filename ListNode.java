/**
 * A node in a list.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class ListNode<E>
{
    private final E element;
    private ListNode<E> next;
    private ListNode<E> prev;
    
    /**
     * Sets the initial element stored in this node.
     * @param element The initial element.
     */
    public ListNode(E element)
    {
        this.element = element;
    }
    
    /**
     * @return The element stored in this node.
     */
    public E getElement()
    {
        return element;
    }
    
    /**
     * @return The next node.
     */
    public ListNode<E> getNext()
    {
        return next;
    }
    
    /**
     * @return The previous node.
     */
    public ListNode<E> getPrev()
    {
        return prev;
    }
    
    /**
     * @param next The next node.
     */
    public void setNext(ListNode<E> next)
    {
        this.next = next;
    }
    
    /**
     * @param prev The previous node.
     */
    public void setPrev(ListNode<E> prev)
    {
        this.prev = prev;
    }
}
