package model;

import java.util.Map;
import java.util.HashMap;

// Project
import model.*;
import view.*;
import controller.*;

/**
 * Write a description of class PropertyWindowFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PropertyWindowFactory
{
    private static PropertyWindowFactory propertyWindowFactory;
    
    private WindowHashSet<PropertyWindow> openPropertyWindows;

    public PropertyWindowFactory()
    {
        openPropertyWindows = new WindowHashSet<>();
    }
    
    public static PropertyWindowFactory getPropertyWindowFactory()
    {
        if (propertyWindowFactory == null)
        {
            propertyWindowFactory = new PropertyWindowFactory();
        }
        return propertyWindowFactory;
    }
    
    public PropertyWindow newPropertyWindow(AirbnbListing listing)
    {
        PropertyWindow propertyWindow = new PropertyWindow(listing);
        if (!(openPropertyWindows.add(propertyWindow)))
        {
            propertyWindow = openPropertyWindows.getElementInSet(propertyWindow);
        }
        else
        {
            propertyWindow.createPropertyWindow();
        }
        propertyWindow.setFront();
        return propertyWindow;
    }
    
    public void propertyWindowClosed(PropertyWindow propertyWindow)
    {
        openPropertyWindows.remove(propertyWindow);
    }
}
