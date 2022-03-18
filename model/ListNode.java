package model;

// Project
import model.*;
import view.*;
import controller.*;

/**
 * A node in a list.
 *
 * @author Adam Murray K21003575
 * @author Augusto Favero K21059800
 * @version 11/03/2022
 */
public class ListNode<E>
{
    private E element;
    private ListNode next;
    private ListNode prev;
    
    /**
     * Constructor for objects of class ListNode
     */
    public ListNode(E element)
    {
        this.element = element;
    }
    
    /**
     * Gets the element stored in this node.
     * @return The element stored in this node.
     */
    public E getElement()
    {
        return element;
    }
    
    /**
     * Gets the next node.
     * @return The next node.
     */
    public ListNode getNext()
    {
        return next;
    }
    
    /**
     * Gets the previous node.
     * @return The previous node.
     */
    public ListNode getPrev()
    {
        return prev;
    }
    
    /**
     * Sets the next node.
     * @param The next node.
     */
    public void setNext(ListNode<E> next)
    {
        this.next = next;
    }
    
    /**
     * Sets the previous node.
     * @param The previous node.
     */
    public void setPrev(ListNode<E> prev)
    {
        this.prev = prev;
    }
}
