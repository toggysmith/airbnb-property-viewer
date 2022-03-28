/**
 * PropertyWindowFactory controls the creation of property windows.
 * Tts main responsibility is to ensure that only one property window exists
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
    private WindowHashSet<PropertyDetailsStage> openPropertyWindows;

    // Constructor for PropertyWindowFactory,
    //Its private as this is a singleton.
    private PropertyDetailsStageFactory()
    {
        openPropertyWindows = new WindowHashSet<>();
    }
    
    /**
     * The method allows other classes to retrive the only object of
     * PropertyWindowFactory and ensures that only one object of this
     * class is ever created.
     * @return The only object of PropertyWindowFactory.
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
     * @param listingId The id for the property you want a window for.
     */
    public PropertyDetailsStage newPropertyWindow(String listingId)
    {
        AirbnbListing listing = ListingProcessor.getListingWithId(listingId);
        return newPropertyWindow(listing);
    }
    
    /**
     * This method attempts to create a property window for the listing given.
     * @param listing The listing for the property you want a window for.
     */
    public PropertyDetailsStage newPropertyWindow(AirbnbListing listing)
    {
        if (listing == null)
        {
            return null;
        }
        
        for (PropertyDetailsStage propertyDetailsStage : openPropertyWindows)
        {
            if (listing.equals(propertyDetailsStage.getListing()))
            {
                propertyDetailsStage.toFront();
                return propertyDetailsStage;
            }
        }
        
        
        PropertyDetailsStage propertyDetailsStage = null;
        
        try
        {
            propertyDetailsStage = new PropertyDetailsStage(listing);
            
            openPropertyWindows.add(propertyDetailsStage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            AlertManager.showTerminatingError("Could not create stage.");
        }
        
        return propertyDetailsStage;
    }
    
    /**
     * This method removes a property window from the set of open windows.
     * @param propertyWindow The property window closed.
     */
    public void propertyWindowClosed(PropertyDetailsStage propertyWindow)
    {
        openPropertyWindows.remove(propertyWindow);
    }
}
