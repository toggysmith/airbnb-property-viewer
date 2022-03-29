import java.util.HashMap;
import java.util.Map;

/**
 * PropertyDetailsStageFactory controls the creation of property stages.
 * Tts main responsibility is to ensure that only one property stage exists
 * for each property at any given time.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class PropertyDetailsStageFactory
{
    private static PropertyDetailsStageFactory propertyDetailsStageFactory;
    
    //The set of all open property windows
    private Map<AirbnbListing, PropertyDetailsStage> openPropertyWindows;

    // Constructor for PropertyWindowFPropertyDetailsStageFactoryactory,
    //Its private as this is a singleton.
    private PropertyDetailsStageFactory()
    {
        openPropertyWindows = new HashMap<>();
    }
    
    /**
     * The method allows other classes to retrive the only object of
     * PropertyDetailsStageFactory and ensures that only one object of this
     * class is ever created.
     * @return The only object of PropertyDetailsStageFactory.
     */
    public static PropertyDetailsStageFactory getPropertyDetailsStageFactory()
    {
        if (propertyDetailsStageFactory == null)
        {
            propertyDetailsStageFactory = new PropertyDetailsStageFactory();
        }
        return propertyDetailsStageFactory;
    }
    
    /**
     * This method takes the listingId given, finds the listing with that id,
     * and then attempts to create a property window for that listing.
     * @param listingId The id for the property you want a stage for.
     */
    public PropertyDetailsStage newPropertyStage(String listingId)
    {
        AirbnbListing listing = ListingProcessor.getListingWithId(listingId);
        return newPropertyStage(listing);
    }
    
    /**
     * This method attempts to create a property stage for the listing given.
     * @param listing The listing for the property you want a window for.
     */
    public PropertyDetailsStage newPropertyStage(AirbnbListing listing)
    {
        if (listing == null)
        {
            return null;
        }
        
        PropertyDetailsStage propertyDetailsStage = openPropertyWindows.get(listing);
        
        if (propertyDetailsStage != null)
        {
            propertyDetailsStage.toFront();
            
            return propertyDetailsStage;
        }
        
        try
        {
            propertyDetailsStage = new PropertyDetailsStage(listing);
            
            openPropertyWindows.put(listing, propertyDetailsStage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            AlertManager.showTerminatingError("Could not create stage.");
        }
        
        return propertyDetailsStage;
    }
    
    /**
     * This method removes a property stage from the set of open windows.
     * @param propertyStage The property stage closed.
     */
    public void propertyStageClosed(PropertyDetailsStage propertyStage)
    {
        openPropertyWindows.remove(propertyStage.getListing());
    }
}
