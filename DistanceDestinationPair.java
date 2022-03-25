/**
 * 
 */
public class DistanceDestinationPair
{
      private DestinationListing destination;
      private int distance;
      
      public DistanceDestinationPair(DestinationListing destination, int distance)
      {
          this.destination = destination;
          this.distance = distance;
      }
      
      public DestinationListing getDestination()
      {
          return destination;
      }
      
      public int getDistance()
      {
          return distance;
      }
}
    