import java.util.List;
/**
 * Enumeration class DestinationType - to represent the two types of destinations that can be loaded from the two different csv files.
 * Depending on which type of destination is passed to the .getDestinations() the DestinationLoader wil return the corresponding list containing all the loaded destinations from the relevant csv file
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public enum DestinationType
{
    PUB,ATTRACTION;
    
    /**
     * A method that returns the corresponding list of destinations depending on which enum type is passed into the function
     * @param DestinationType, either PUB, ATTRACTION, to return the relevant list of destinations that has been loaded from the csv
     * @return List<DestinationListing>, a list containing all the loaded data for the passed destination type
     */
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
