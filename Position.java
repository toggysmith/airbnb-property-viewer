/**
 * Responsible for holding the position of a listing.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class Position
{
    private double latitude;
    private double longitude;

    /**
     * Constructor for objects of class Position
     * @param latitude The latitude.
     * @param longitude The longitude.
     */
    public Position(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**
     * @return The latitude.
     */
    public double getLatitude()
    {
        return latitude;
    }
    
    /**
     * @return The longitude.
     */
    public double getLongitude()
    {
        return longitude;
    }
}
