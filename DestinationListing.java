
/**
 * Class to store all the famous destinations
 *
 * @author (Augusto Favero k21059800)
 * @version (v1)
 */
public class DestinationListing
{
    private String destinationName;
    private double longitude;
    private double latitude;
    private String address;
    private String boroughName;
    private String price;
    
    public DestinationListing(String destinationName, String address, double longitude, double latitude,String boroughName, String price)
    {
        this.destinationName = destinationName;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.boroughName = boroughName;
        this.price = price;
        
        if(destinationName == null || address == null || longitude == 0 || latitude == 0 || boroughName == null || price == null){
            throw new IllegalStateException("empty value entered");
            
        }
    }
    
    public String getDestinationName()
    {
        return destinationName;
    }
    
    public double getLongitude()
    {
        return longitude;
    }
    
    public double getLatitude()
    {
        return latitude;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public String getBorough()
    {
        return boroughName;
    }
    
    public String getPrice()
    {
        return price;
    }
}
