import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
/**
 * Write a description of class testSmallestDistance here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class testSmallestDistance
{
    private HashMap<Integer, List<String>> closestFive;

    //every time one of the two interactivity stats is displayed
    
    private int mapSize = 0;

    public testSmallestDistance()
    {
        closestFive = new  HashMap<Integer, List<String>>();
    }
    
    public HashMap<Integer, List<String>> getClosestFive()
    {
        return closestFive;    
    }
    
    public void addSmallest(int distance, String place )
    {
       if(mapSize == 0 || checkMapSize()){
           closestFive.computeIfAbsent(distance, k -> new ArrayList<>()).add(place);
           mapSize++;
           return;
       }
       
        //adds duplicate distances //add else to check that the duplicate could be added even if the size is bigger then three
        if(closestFive.containsKey(distance) && checkMapSize()){
            closestFive.get(distance).add(place);
            int total = closestFive.get(distance).size();
            mapSize = mapSize + total;
            return;
       }else if(closestFive.containsKey(distance) && !checkMapSize()){
           int currentMax = getBiggest();
           
           if(distance < currentMax){
               int toRemove = closestFive.get(currentMax).size();            
               closestFive.remove(currentMax);
               closestFive.get(distance).add(place);
               return;
           }
           return;
       }
              
       if(!checkMapSize()){
           int currentMax = getBiggest();
           
           if(distance < currentMax){
               closestFive.remove(currentMax);
               closestFive.computeIfAbsent(distance, k -> new ArrayList<>()).add(place);
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
}
