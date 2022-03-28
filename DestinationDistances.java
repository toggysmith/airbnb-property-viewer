import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Comparator;
import java.util.Collections;
/**
 * DestinationDistances class controls the main mathematical functionality of the calculation for the five closest locations (pubs or tourist attraction relevant to a propety selected by
 * the user)
 * 
 * This class creates an array list of DistanceDestinationPair , adding all the destinations from the passed DestinationListing List and their corresponding calculated distance
 * from the selectedProperty by the user.
 * 
 * This class is then sorted an only the first five elements are returned which represent the five closest destinations to the selected property
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 * 
 */
public class DestinationDistances
{
    public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    
    private ArrayList<DistanceDestinationPair> destinations;
    //every time one of the two interactivity stats is displayed
    
    /**
     * DestinationDistances constructor creates a new array list for all the destination and distance pairs for all relevant destinations
     * @param List<DestinationListing> filteredDestinations, the list of filtered destinations that fit the user selected preferences
     * @param AirbnbListing selectedProperty, the property selected by the user
     */
    public DestinationDistances(List<DestinationListing> filteredDestinations, AirbnbListing selectedProperty)
    {
        destinations = new ArrayList<>();
        addDestinations(filteredDestinations,selectedProperty);
    }
    
    /*
     * The filtered destinations are iterated over and their relative distance is calculated using the selected property as a reference point
     * @param List<DestinationListing> filteredDestinations, the list of filtered destinations that fit the user selected preferences
     * @param AirbnbListing selectedProperty, the property selected by the user
     */
    private void addDestinations(List<DestinationListing> filteredDestinations, AirbnbListing selectedProperty)
    {
        for(DestinationListing destination: filteredDestinations){
             int calculatedDistance = calculateDistance(selectedProperty.getLongitude(), destination.getLongitude(), selectedProperty.getLatitude(), destination.getLatitude());
             destinations.add(new DistanceDestinationPair(destination, calculatedDistance)); 
        }
    }
   
    /**
     *Converts the destinations ArrayList<DestinationPair> to an array which is sorted using a merge sort and the first five elements in the list are retrieved. If there are less then five locations, all of them are displayed,
     *otherwise the first five are chosen which represent the five closest destinations
     *@return ArrayList<DistanceDestinationPair>, containing the up to five closest destinations relative to the selected property
     */
    public ArrayList<DistanceDestinationPair> getFiveSmallest()
    {
        ArrayList<DistanceDestinationPair> smallestFive = new ArrayList<DistanceDestinationPair>();
        
        DistanceDestinationPair[] pairs = new DistanceDestinationPair[destinations.size()];
        
        for(int i = 0; i <= pairs.length - 1; i++){
              pairs[i] = destinations.get(i);
        }
        
        mergeSort(pairs,pairs.length);
        if(pairs.length > 5){
            for(int i = 0; i < 5; i++){
            smallestFive.add(pairs[i]);
            }
        }else{
            for(int i = 0; i <= pairs.length - 1; i++){
            smallestFive.add(pairs[i]);
            }
        }
        return smallestFive;
    }
    
    //reference : https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude (1st answer)
    /*
     * given the property and destination longitude and latitude calculateDistancew() calculates the distance between them
     * @return int, returns the distance between the two locations in KM as an int 
     */
    private int calculateDistance(double propertyLong, double venueLong, double propertyLat, double venueLat)
    {
        
        double latDistance = Math.toRadians(propertyLat - venueLat);
        double lngDistance = Math.toRadians(propertyLong - venueLong);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
          + Math.cos(Math.toRadians(propertyLat)) * Math.cos(Math.toRadians(venueLat))
          * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
    }
    
    //reference: https://www.baeldung.com/java-merge-sort
    /**
     *Merge sort algorithm that sorts the inputed array in ascending order, sorting the DistanceDestinationPair object from shortest distance to longest distance 
     */
    public void mergeSort(DistanceDestinationPair[] a, int n)
    {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        DistanceDestinationPair[] l = new DistanceDestinationPair[mid];
        DistanceDestinationPair[] r = new DistanceDestinationPair[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    private void merge(DistanceDestinationPair[] a, DistanceDestinationPair[] l, DistanceDestinationPair[] r, int left, int right)
    {
 
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getDistance() <= r[j].getDistance()) {
                a[k++] = l[i++];
            }
            else{
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
