import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Comparator;
import java.util.Collections;
/**
 * Write a description of class SmallestDistance here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DestinationDistances
{
    public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    
    //private HashMap<Integer, List<DestinationListing>> closestFive;
    private ArrayList<DistanceDestinationPair> destinations;
    //every time one of the two interactivity stats is displayed
    
    public DestinationDistances()
    {
        destinations = new ArrayList<>();
    }
    
    
    public void addDestinations(List<DestinationListing> filteredDestinations, AirbnbListing selectedProperty)
    {
       for(DestinationListing destination: filteredDestinations){
            int calculatedDistance = calculateDistance(selectedProperty.getLongitude(), destination.getLongitude(), selectedProperty.getLatitude(), destination.getLatitude());
            destinations.add(new DistanceDestinationPair(destination, calculatedDistance)); 
        }
       
    }
   
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
    
    public void mergeSort(DistanceDestinationPair[] a, int n) {
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

    private void merge(DistanceDestinationPair[] a, DistanceDestinationPair[] l, DistanceDestinationPair[] r, int left, int right) {
 
    int i = 0, j = 0, k = 0;
    while (i < left && j < right) {
        if (l[i].getDistance() <= r[j].getDistance()) {
            a[k++] = l[i++];
        }
        else {
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
