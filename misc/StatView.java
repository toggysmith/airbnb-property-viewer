package misc;

import java.util.*;
import java.util.stream.*;
import java.util.ArrayList;

// Project
import model.*;
import view.*;
import controller.*;

/**
 * Write a description of class StatView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatView
{
    private static List<AirbnbListing> airbnbListings;
    private static final String roomNeeded = "Entire home/apt";
    public int averagePropertyView() {
         int average = airbnbListings.stream()
                       .mapToInt(listing -> listing.getNumberOfReviews())
                       .sum();
         long count = airbnbListings.stream()
                     .count();
        int l = (int)count;
                     return average/l;
                     
    }
    
    public int totalAvailableProperties(int lower, int upper) {
        long available = airbnbListings.stream()
                    .filter(listing -> listing.getAvailability365() > 0 && listing.getPrice() >= lower && listing.getPrice() <= upper)
                    .count();
        int total = (int)available;
        return total;
    }
    
    public int nonPrivateRoom(int lower, int upper) {
        long nonPrivate = airbnbListings.stream()
                                        .filter(listing -> listing.getRoom_type().equals(roomNeeded) && listing.getPrice() >= lower && listing.getPrice() <= upper)
                                        .count();
                                        
        int privateCount = (int)nonPrivate;
        return privateCount;
    }
    
    
    
    public ArrayList<AirbnbListing> listPrice() {
        return airbnbListings.stream()
                             .filter(listing -> listing.getPrice() > 0)
                             .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<AirbnbListing> listMinimumNights() {
        return airbnbListings.stream()
                             .filter(listing -> listing.getMinimumNights() > 0)
                             .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<AirbnbListing> expensiveNeighbourhood() {
        Iterator<AirbnbListing> it1 = listPrice().iterator();
        Iterator<AirbnbListing> it2 = listMinimumNights().iterator();
        int max = 0;
        int price= 0;
        int nights = 0;
        for(AirbnbListing x: listPrice()) {
            int calcPrice = x.getPrice() * x.getMinimumNights();
            if(calcPrice > max) {
                max = calcPrice;
                price = x.getPrice();
                nights = x.getMinimumNights();
            }
            
        }
        final int finalPrice = price;
        final int finalNight = nights;
        
        return airbnbListings.stream()
                             .filter(listing -> listing.getPrice() == finalPrice && listing.getMinimumNights() == finalNight)
                             .collect(Collectors.toCollection(ArrayList::new));
        //Will return an arraylist with one element
    }
    
}