import java.util.ArrayList;
/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 
public class test
{
    public static void method()
    {
        DestinationDistances test = new DestinationDistances();
        ArrayList<DistanceDestinationPair> destinations = new ArrayList<>();
        
        test.add("Augusto", 10);
        test.add("Augusto", 11);
        test.add("Augusto", 12);
        test.add("Augusto", 11);
        test.add("Mathew", 37);
        
        destinations = test.getFiveSmallest();
    }
}
*/