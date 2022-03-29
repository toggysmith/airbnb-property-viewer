import java.util.Map;
import java.util.HashMap;

/**
 * DestinationDetailFactory controls the creation of destination details.
 * Tts main responsibility is to ensure that only one destination detail stage exists
 * for each destination at any given time.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class DestinationDetailsFactory
{
    private static DestinationDetailsFactory destinationDetailFactory;
    
    //The set of all open destination detail stages
    private Map<DestinationListing, DestinationDetailsStage> openDestinationDetails;
    
    /*
     * Constructor for DestinationDetailFactory,
     * Its private as this is a singleton.
     */
    private DestinationDetailsFactory()
    {
        openDestinationDetails = new HashMap<>();
    }
    
    /**
     * The method allows other classes to retrive the only object of
     * DestinationDetailFactory and ensures that only one object of this
     * class is ever created.
     * @return The only object of DestinationDetailFactory.
     */
    public static DestinationDetailsFactory getDestinationDetailFactory()
    {
        if (destinationDetailFactory == null)
        {
            destinationDetailFactory = new DestinationDetailsFactory();
        }
        return destinationDetailFactory;
    }
    
    /**
     * This method attempts to create a destination detail for the listing given.
     * @param listing The listing for the destination you want a detail for.
     */
    public DestinationDetailsStage newDestinationDetail(DestinationListing listing)
    {
        if (listing == null)
        {
            return null;
        }
        
        DestinationDetailsStage DestintionDetailsStage = openDestinationDetails.get(listing);

        if (DestintionDetailsStage != null)
        {
            DestintionDetailsStage.toFront();

            return DestintionDetailsStage;
        }

        try
        {
            DestintionDetailsStage = new DestinationDetailsStage(listing);

            openDestinationDetails.put(listing, DestintionDetailsStage);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            AlertManager.showTerminatingError("Could not create stage.");
        }

        return DestintionDetailsStage;
    }
    
    /**
     * This method removes a destination detail from the set of open details.
     * @param destinationDetail The destination detail closed.
     */
    public void destinationDetailClosed(DestinationDetailsStage destinationDetail)
    {
        openDestinationDetails.remove(destinationDetail);
    }
}
