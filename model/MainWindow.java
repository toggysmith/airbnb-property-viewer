package model;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

// Project
import model.*;
import view.*;
import controller.*;


/**
 * Write a description of class MainWindow here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainWindow
{
    private static MainWindow mainWindow;
    
    private List<AirbnbListing> airbnbListings;
    private MainController mainController;
    private MainView mainView;
    private RangeValues rangeValues;

    /**
     * Constructor for objects of class MainWindow
     */
    private MainWindow()
    {
        airbnbListings = AirbnbDataLoader.getListings();
        try
        {
            mainView = new MainView(this);
            mainController = mainView.getMainController();
            rangeValues = mainController.getRangeValues();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public MainController getMainController()
    {
        return mainController;
    }
    
    public MainView getMainView()
    {
        return mainView;
    }
    
    public static MainWindow getMainWindow()
    {
        if (mainWindow == null)
        {
            mainWindow = new MainWindow();
        }
        
        return mainWindow;
    }
    
    public RangeValues getRangeValues()
    {
        return rangeValues;
    }
    
    /**
     * Get the listings in a specific borough.
     * @return The listings in a specific borough.
     */
    public ObservableList<AirbnbListing> getListingsInBorough(String targetBorough)
    {
        List<AirbnbListing> listings = AirbnbDataLoader.getListings();
        
        listings = ListingManipulator.filterByBorough(listings, targetBorough);
        
        ObservableList<AirbnbListing> returnListings = FXCollections.observableArrayList();

        for (AirbnbListing listing : listings)
            returnListings.add(listing);
        
        return returnListings;
    }
}
