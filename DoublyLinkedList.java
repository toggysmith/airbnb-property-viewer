
/**
 * A doubly linked list used to keep track of all the different panes in our application and the order in which they are accessed
 * The head and tail node are empty pointers used to simply represent the beginning and ending of the order of panes.
 * If the last pane is selected and the next button in the GUI is pressed then the application loops back to the head and the first item it points to
 *
 * @author Adam Murray K21003575
 * @author Augusto Favero K21059800
 * @version 11/03/2022
 */
public class DoublyLinkedList<E>
{
    private ListNode<E> head; 
    private ListNode<E> tail;
    
    private int size;
    
    /**
     * Constructor for objects of class CircularLinkedList
     */
    public DoublyLinkedList()
    {
        size = 0;
        
        head = new ListNode<E>(null);
        tail = new ListNode<E>(null);
        
        head.setNext(tail);
        tail.setPrev(head);
        
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    /**
     * Gets the next element from the list.
     * @param the current element as a relative position to what the next element should be
     * @return The next element in the list.
     */
    public E getNextElement(E currentElement)
    {
        //if (head == null)
        //{
          //  return null;
        //}
        
        if(isEmpty()){
            return null;
        }
        
        //finds the node storing the element currentElement
        ListNode<E> currentNode =  findNode(currentElement);
        
        if(currentNode == null){
            return null; // throw error exception
        }
        
        //find the next node relative to the currentNode
        ListNode<E> nextNode = currentNode.getNext();
        
        //loop back to the head
        if(nextNode.equals(tail)){
            ListNode<E> firstNode = head.getNext();
            return firstNode.getElement();
        }
        
        //returns the element of this next node
        return nextNode.getElement(); 
    }
    
    public E getPrevElement(E currentElement)
    {
        if(isEmpty()){
            return null;
        }
        
        //finds the node storing the element currentElement 
        ListNode<E> currentNode =  findNode(currentElement);
        
        if(currentNode == null){
            return null;
        }
        
        ListNode<E> prevNode = currentNode.getPrev();
        
        if(prevNode.equals(head)){
            ListNode<E> lastNode = tail.getPrev();
            return lastNode.getElement();
        }
        
        return prevNode.getElement();
    }
    
    private ListNode<E> findNode(E toFindElement)
    {
        if(isEmpty()){
            return null; // add exception
        }
        
        ListNode<E> currentNode = head.getNext();
        
        
        for(int i = 0; i <= size; i++){
            E currentElement = currentNode.getElement();
            
            if(currentElement.equals(toFindElement)){
               return currentNode;  
            }
            
            //gets the next element the curent node is pointing to
            currentNode = currentNode.getNext();
        }
        
        return null;
    }
    
    /**
     * Adds an element to the list.
     * The element is added so that it will appear after the previously added element.
     * @param element The element to be added.
     */
    public void add(E element)
    {
        /**
        
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
        */
       
       
       ListNode<E> newNode = new ListNode(element);
       
       ListNode<E> beforeTail = tail.getPrev(); 
       
       tail.setPrev(newNode);
       beforeTail.setNext(newNode);
           
       newNode.setNext(tail);
       newNode.setPrev(beforeTail);
           
       size++;
    }
}
