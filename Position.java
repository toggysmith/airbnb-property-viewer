
/**
 * Write a description of class Position here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Position
{
    // instance variables - replace the example below with your own
    private double latitude;
    private double longitude;

    /**
     * Constructor for objects of class Position
     */
    public Position(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public double getLatitude()
    {
        return latitude;
    }
    
    public double getLongitude()
    {
        return longitude;
    }
}
