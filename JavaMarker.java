
/**
 * Write a description of class JavaMarker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JavaMarker
{
    private static PropertyWindowFactory propertyWindowFactory = PropertyWindowFactory.getPropertyWindowFactory();
    public void openPropertyWindow(String listingId)
    {
        propertyWindowFactory.newPropertyWindow(listingId);
    }
}
