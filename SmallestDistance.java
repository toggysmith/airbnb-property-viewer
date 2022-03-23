import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
/**
 * Write a description of class SmallestDistance here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmallestDistance
{
    public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    private HashMap<Integer, List<DestinationListing>> closestFive;

    //every time one of the two interactivity stats is displayed
    
    private int mapSize = 0;
    
    //reference https://www.baeldung.com/java-map-duplicate-keys
    public void addSmallest(DestinationListing destination, AirbnbListing selectedProperty)
    {
       int calculatedDistance = calculateDistance(selectedProperty.getLongitude(), destination.getLongitude(), selectedProperty.getLatitude(), destination.getLatitude());
        
        //adds duplicate distances //add else to check that the duplicate could be added even if the size is bigger then three
        if(closestFive.containsKey(calculatedDistance) && checkMapSize()){
            closestFive.get(calculatedDistance).add(destination);
            mapSize++;
            return;
       }else if(closestFive.containsKey(calculatedDistance) && !checkMapSize()){
           int currentMax = getBiggest();
           
           if(calculatedDistance < currentMax){
               closestFive.remove(currentMax);
               closestFive.get(calculatedDistance).add(destination);
           }
       }
              
       if(checkMapSize()){
            closestFive.computeIfAbsent(calculatedDistance, k -> new ArrayList<>()).add(destination);
            mapSize++;
       }else{
           int currentMax = getBiggest();
           
           if(calculatedDistance < currentMax){
               closestFive.remove(currentMax);
               closestFive.computeIfAbsent(calculatedDistance, k -> new ArrayList<>()).add(destination);
           }
       }
        
    }
   
    private int getBiggest()
    {
        Set<Integer> distances = closestFive.keySet();
        Iterator<Integer> it = distances.iterator();
        
        int max = it.next();
        
        while(it.hasNext()){
            if(it.next() >= max){
                max = it.next();
            }
        }
        
        return max;
    }
    
    private boolean checkMapSize()
    {
        return mapSize < 5;
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
    
}
