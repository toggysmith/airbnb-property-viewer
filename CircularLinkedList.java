/**
 * A circular list that has no end and no beginning, only the current position.
 *
 * @author Adam Murray K21003575
 * @version 11/03/2022
 */
public class CircularLinkedList<E> implements CircularList<E>
{
    private ListNode<E> currentNode;
    private ListNode<E> lastAdded;
    private int size;
    
    /**
     * Constructor for objects of class CircularLinkedList
     */
    public CircularLinkedList()
    {
        size = 0;
    }
    
    /**
     * Gets the next element from the list.
     * @return The next element in the list.
     */
    public E getNext()
    {
        if (currentNode == null)
        {
            return null;
        }
        currentNode = currentNode.getNext();
        return currentNode.getElement();
    }
    
    /**
     * Gets the previous element from the list.
     * @return The previous element in the list.
     */
    public E getPrev()
    {
        if (currentNode == null)
        {
            return null;
        }
        currentNode = currentNode.getPrev();
        return currentNode.getElement();
    }
    
    /**
     * Adds an element to the list.
     * The element is added so that it will appear after the previously added element.
     * @param element The element to be added.
     */
    public void add(E element)
    {
        ListNode newNode = new ListNode(element);
        ListNode nextNode;
        if (lastAdded == null)
        {
            lastAdded = newNode;
            nextNode = newNode;
        }
        else
        {
            nextNode = lastAdded.getNext();
        }
        newNode.setNext(nextNode);
        newNode.setPrev(lastAdded);
        lastAdded.setNext(newNode);
        nextNode.setPrev(newNode);
        if (size == 0)
        {
            currentNode = newNode;
        }
        lastAdded = newNode;
        size++;
    }
}
