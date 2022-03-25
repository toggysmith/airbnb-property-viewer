import java.util.List;
/**
 * Enumeration class DestinationType - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum DestinationType
{
    PUB,ATTRACTION;
    
    public static List<DestinationListing>  getDestinations(DestinationType type)
    {
        if(type.equals(DestinationType.PUB)){
            return DestinationLoader.getPubs();
            
        }else if(type.equals(DestinationType.ATTRACTION)){
            return DestinationLoader.getTouristDestinations();
        }
        return null;
    }
}
