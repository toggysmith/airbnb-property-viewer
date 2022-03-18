package model;

import java.util.HashSet;

// Project
import model.*;
import view.*;
import controller.*;

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
