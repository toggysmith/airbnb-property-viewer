/**
 * This stores a destination along with its distance from a given property.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */ 
public class DistanceDestinationPair
{
    private DestinationListing destination;
    private int distance;
    
    /**
     * Constructor for DistanceDestinationPair.
     * @param destination The destination listing that is being measured.
     * @param distance The distance that the destination is from a property.
     */
    public DistanceDestinationPair(DestinationListing destination, int distance)
    {
        this.destination = destination;
        this.distance = distance;
    }

    /**
     * @return The destination.
     */
    public DestinationListing getDestination()
    {
        return destination;
    }

    /**
     * @return The distance that the destination is from a property.
     */
    public int getDistance()
    {
        return distance;
    }
}
 