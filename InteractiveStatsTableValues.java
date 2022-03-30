/**
 * InteractiveStatsTableValues holds the values in each row for the table in the statistics interactive pane.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class InteractiveStatsTableValues
{
    private DistanceDestinationPair distanceDestinationPair;
    private String name;
    private String address;
    private String distance;
    
    /**
     * Constructor for objects of class InteractiveStatsTableValues.
     */
    public InteractiveStatsTableValues(DistanceDestinationPair distanceDestinationPair)
    {
        this.distanceDestinationPair = distanceDestinationPair;
        
        name = distanceDestinationPair.getDestination().getDestinationName();
        address = distanceDestinationPair.getDestination().getAddress();
        distance = String.format("%s km", distanceDestinationPair.getDistance());
    }

    /**
     * @return The name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return The address.
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * @return The distance.
     */
    public String getDistance()
    {
        return distance;
    }
    
    /**
     * @return The DistanceDestinationPair.
     */
    public DistanceDestinationPair getDistanceDestinationPair()
    {
        return distanceDestinationPair;
    }
}
