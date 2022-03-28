
/**
 * Class to store all the tourist attraction destinations and pubs from the various csv files. The csv files have a similar structure therefore this class is used to dynamically represent both the attractions and pubs
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class DestinationListing
{
    //the name of the destination
    private String destinationName;
    
    //longitude and latitude of the destination
    private double longitude;
    private double latitude;
    
    //address of the destination
    private String address;
    
    //the borough in which the destination exists
    private String boroughName;
    
    //price for attractions refers to the ticket price, for pubs its how expensive the pub is using (£, ££, £££) metrics
    private String price;
    
    /**
     * DestinationListing constructor encapsulates the data from each row from the relevant loaded csv files into a single
     */
    public DestinationListing(String destinationName, String address, double longitude, double latitude,String boroughName, String price)
    {
        this.destinationName = destinationName;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.boroughName = boroughName;
        this.price = price;
        
        /**
        if(destinationName == null || address == null || longitude == 0 || latitude == 0 || boroughName == null || price == null){
            throw new IllegalStateException("empty value entered");
            
        }
        */
    }
    
    /**
     * Getter method that returns the name of the DestinationListing object
     * @return String , the destination name
     */
    public String getDestinationName()
    {
        return destinationName;
    }
    
    /**
     * Getter method that returns the longitude of the DestinationListing object
     * @return double, the longitude
     */
    public double getLongitude()
    {
        return longitude;
    }
    
    /**
     * Getter method that returns the latitude of the DestinationListing object
     * @return double, the latitude
     */
    public double getLatitude()
    {
        return latitude;
    }
    
    /**
     * Getter method that returns the address of the DestinationListing object
     * @return String, the destination address
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * Getter method that returns the borough name of the DestinationListing object
     * @return String, the borough name
     */
    public String getBorough()
    {
        return boroughName;
    }
    
    /**
     * getter method String that returns the price for the DestinationListing object, for attractions its a ticket price , for pubs is a metric of how expensive the pub is 
     * @return String,the price
     */
    public String getPrice()
    {
        return price;
    }
}
