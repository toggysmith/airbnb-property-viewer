import java.util.ArrayList;
import java.util.List;

/**
 * DestinationDistances class controls the main mathematical functionality of the calculation for the five closest locations (pubs or
 * tourist attractions relevant to a property selected by the user).
 * 
 * This class creates an array list of DistanceDestinationPair, adding all the destinations from the passed DestinationListing list
 * and their corresponding calculated distances from the selected property by the user as a reference point.
 * 
 * This class is then sorted and only the first five elements are returned which represent the five closest destinations to the
 * selected property.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class DestinationDistances
{
    private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    
    private ArrayList<DistanceDestinationPair> destinations; // Every time one of the two interactivity stats is displayed.
    
    /**
     * Creates a new array list for all the destination and distance pairs for all relevant destinations.
     * @param filteredDestinations The list of filtered destinations that fit the user selected preferences.
     * @param selectedProperty The property selected by the user.
     */
    public DestinationDistances(List<DestinationListing> filteredDestinations, AirbnbListing selectedProperty)
    {
        destinations = new ArrayList<>();
        addDestinations(filteredDestinations,selectedProperty);
    }
   
    /**
     * Converts the destinations ArrayList<DestinationPair> to an array which is sorted using a merge sort and the first five
     * elements in the list are retrieved. If there are less then five locations, all of them are displayed, otherwise the first
     * five are chosen which represent the five closest destinations.
     * @return Containing the up to five closest destinations relative to the selected property.
     */
    public ArrayList<DistanceDestinationPair> getFiveSmallest()
    {
        // If there's nothing in the destination list, return nothing.
        ArrayList<DistanceDestinationPair> smallestFive = new ArrayList<DistanceDestinationPair>();
        
        if (destinations.size() == 0)
        {
            return smallestFive;
        }
        
        // Convert the ArrayList into an array.
        DistanceDestinationPair[] pairs = destinations.toArray(new DistanceDestinationPair[destinations.size()]);
        
        // Find the first five elements in the pairs array.
        mergeSort(pairs, pairs.length);
        
        for (int i = 0; i < 5 && i < pairs.length; i++)
        {
            smallestFive.add(pairs[i]);
        }
        
        return smallestFive;
    }
    
    /*
     * The filtered destinations are iterated over and their relative distance is calculated using the selected property as a
     * reference point.
     * @param filteredDestinations The list of filtered destinations that fit the user selected preferences.
     * @param selectedProperty The property selected by the user.
     */
    private void addDestinations(List<DestinationListing> filteredDestinations, AirbnbListing selectedProperty)
    {
        for (DestinationListing destination: filteredDestinations)
        {
            int calculatedDistance = calculateDistance(selectedProperty.getLongitude(), destination.getLongitude(),
                                                       selectedProperty.getLatitude(), destination.getLatitude());
            
            destinations.add(new DistanceDestinationPair(destination, calculatedDistance)); 
        }
    }
    
    // Reference : https://stackoverflow.com/a/16794680
    /*
     * Given the property and destination longitude and latitude calculateDistance() calculates the distance between them.
     * @param propertyLong The longitude of the property.
     * @param venueLong The longitude of the venue.
     * @param propertyLat The latitude of the property.
     * @param venueLat The latitude of the venue.
     * @return The distance between the two locations in KM as an int.
     */
    private int calculateDistance(double propertyLong, double venueLong, double propertyLat, double venueLat)
    {
        double latDistance = Math.toRadians(propertyLat - venueLat);
        double longDistance = Math.toRadians(propertyLong - venueLong);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                   + Math.cos(Math.toRadians(propertyLat)) * Math.cos(Math.toRadians(venueLat))
                   * Math.sin(longDistance / 2) * Math.sin(longDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
    }
    
    // Reference: https://www.baeldung.com/java-merge-sort
    /**
     * Merge sort algorithm that sorts the inputted array in ascending order, sorting the DistanceDestinationPair object from
     * shortest distance to longest distance.
     */
    private void mergeSort(DistanceDestinationPair[] a, int n)
    {
        if (n < 2)
        {
            return;
        }
        
        int mid = n / 2;
        
        DistanceDestinationPair[] l = new DistanceDestinationPair[mid];
        DistanceDestinationPair[] r = new DistanceDestinationPair[n - mid];

        for (int i = 0; i < mid; i++)
        {
            l[i] = a[i];
        }
        
        for (int i = mid; i < n; i++)
        {
            r[i - mid] = a[i];
        }
        
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    
    /*
     * Reference: https://www.baeldung.com/java-merge-sort
     */
    private void merge(DistanceDestinationPair[] a, DistanceDestinationPair[] l, DistanceDestinationPair[] r, int left, int right)
    {
        int i = 0, j = 0, k = 0;
        
        while (i < left && j < right)
        {
            if (l[i].getDistance() <= r[j].getDistance())
            {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        
        while (i < left)
        {
            a[k++] = l[i++];
        }
        
        while (j < right)
        {
            a[k++] = r[j++];
        }
    }
}
