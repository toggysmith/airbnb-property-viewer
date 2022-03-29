/**
 * This class is given to a Js file so that it
 * can act as a bridge and its methods can be 
 * called from within the Js file.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */ 
public class JsToJavaBridge
{
    private static PropertyDetailsStageFactory propertyDetailsStageFactory = PropertyDetailsStageFactory.getPropertyDetailsStageFactory();
    
    /**
     * This creates a new property window for the listing given.
     * @param listingId The id for the AirbnbListing.
     */
    public void openPropertyWindow(String listingId)
    {
        propertyDetailsStageFactory.newPropertyStage(listingId);
    }
    
}
