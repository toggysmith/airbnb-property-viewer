
/**
 * Write a description of class PropertyWindow here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PropertyWindow
{

    /**
     * Constructor for objects of class PropertyWindow
     */
    public PropertyWindow(AirbnbListing listing)
    {
        try
        {
            new PropertyWindowView(listing);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
