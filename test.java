import java.util.List;
import java.util.HashMap;

/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test
{
    public static void method()
    {
        //List<DestinationListing> des = DestinationLoader.getPubs();
        //List<DestinationListing> dess = DestinationLoader.getTouristDestinations();
        
        testSmallestDistance test = new testSmallestDistance();
        HashMap<Integer, String> destinations = new HashMap<>();
        
        destinations.put(10, "Augusto");
        destinations.put(11, "Augusto");
        destinations.put(12, "Augusto");
        destinations.put(13, "Augusto");
        destinations.put(14, "Augusto");
         
        for(Integer eachint: destinations.keySet()){
            test.addSmallest(eachint, destinations.get(eachint));
        }
        
        
    }
}
