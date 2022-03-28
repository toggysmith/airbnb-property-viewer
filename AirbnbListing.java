/**
 * Represents one listing of a property for rental on Airbnb.
 * This is essentially one row in the data table. Each column
 * has a corresponding field.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */ 
public class AirbnbListing {
    /**
     * The id and name of the individual property
     */
    private String id;
    private String name;
    
    /**
     * The id and name of the host for this listing.
     * Each listing has only one host, but one host may
     * list many properties.
     */
    private String host_id;
    private String host_name;

    /**
     * The grouped location to where the listed property is situated.
     * For this data set, it is a london borough.
     */
    private String neighbourhood;

    /**
     * The location on a map where the property is situated.
     */
    private double latitude;
    private double longitude;

    /**
     * The type of property, either "Private room" or "Entire Home/apt".
     */
    private String room_type;

    /**
     * The price per night's stay
     */
    private int price;

    /**
     * The minimum number of nights the listed property must be booked for.
     */
    private int minimumNights;
    private int numberOfReviews;

    /**
     * The date of the last review, but as a String
     */
    private String lastReview;
    private double reviewsPerMonth;

    /**
     * The total number of listings the host holds across AirBnB
     */
    private int calculatedHostListingsCount;
    /**
     * The total number of days in the year that the property is available for
     */
    private int availability365;

    /**
     * Constructor for AirbnbListing.
     * @param id The id of the listing.
     * @param name The name of the listing.
     * @param host_id The id of the host.
     * @param host_name The name of the host.
     * @param neighbourhood The borough.
     * @param latitude The latitude.
     * @param longitude The longitude.
     * @param room_type The room type.
     * @param price The price per night.
     * @param minimumNights The minimum number nights you can stay in a listing.
     * @param numberOfReviews The number of reviews.
     * @param lastReview The last review.
     * @param reviewsPerMonth The number of reviews per month for the listing.
     * @param calculatedHostListingsCount The number of listings by that host.
     * @param availability365 The total number of days in the year that the property is available for.
     */
    public AirbnbListing(String id, String name, String host_id,
                         String host_name, String neighbourhood, double latitude,
                         double longitude, String room_type, int price,
                         int minimumNights, int numberOfReviews, String lastReview,
                         double reviewsPerMonth, int calculatedHostListingsCount, int availability365) {
        this.id = id;
        this.name = name;
        this.host_id = host_id;
        this.host_name = host_name;
        this.neighbourhood = neighbourhood;
        this.latitude = latitude;
        this.longitude = longitude;
        this.room_type = room_type;
        this.price = price;
        this.minimumNights = minimumNights;
        this.numberOfReviews = numberOfReviews;
        this.lastReview = lastReview;
        this.reviewsPerMonth = reviewsPerMonth;
        this.calculatedHostListingsCount = calculatedHostListingsCount;
        this.availability365 = availability365;
    }

    /**
     * @return The id of the listing.
     */
    public String getId() {
        return id;
    }

    /**
     * @return The name of the listing.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The id of the host.
     */
    public String getHost_id() {
        return host_id;
    }

    /**
     * @return The name of the host.
     */
    public String getHost_name() {
        return host_name;
    }

    /**
     * @return The borough.
     */
    public String getNeighbourhood() {
        return neighbourhood;
    }

    /**
     * @return The latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return The longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return The room type.
     */
    public String getRoom_type() {
        return room_type;
    }

    /**
     * @return The price per night.
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return The minimum number nights you can stay in a listing.
     */
    public int getMinimumNights() {
        return minimumNights;
    }

    /**
     * @return The number of reviews.
     */
    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    /**
     * @return The last review.
     */
    public String getLastReview() {
        return lastReview;
    }

    /**
     * @return The number of reviews per month for the listing.
     */
    public double getReviewsPerMonth() {
        return reviewsPerMonth;
    }

    /**
     * @return The number of listings by that host.
     */
    public int getCalculatedHostListingsCount() {
        return calculatedHostListingsCount;
    }

    /**
     * @return The availability.
     */
    public int getAvailability365() {
        return availability365;
    }

    /**
     * Over rides the toString method to now output all the details of this listing.
     * Returns "AirbnbListing{}" with all its details inside the {} 
     * in the form: detail name + "='" + detail + "\'"
     * @return The AirbnbLisings details.
     */
    @Override
    public String toString() {
        return "AirbnbListing{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", host_id='" + host_id + '\'' +
                ", host_name='" + host_name + '\'' +
                ", neighbourhood='" + neighbourhood + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", room_type='" + room_type + '\'' +
                ", price=" + price +
                ", minimumNights=" + minimumNights +
                ", numberOfReviews=" + numberOfReviews +
                ", lastReview='" + lastReview + '\'' +
                ", reviewsPerMonth=" + reviewsPerMonth +
                ", calculatedHostListingsCount=" + calculatedHostListingsCount +
                ", availability365=" + availability365 +
                '}';
    }
    
    /**
     * The equals of the Airbnb listing is now dependent on its id.
     * 
     * @return True if the object has the same id, false if not.
     */
    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        
        if (!(object instanceof AirbnbListing))
        {
            return false;
        }
        
        AirbnbListing listing = (AirbnbListing) object;
        
        return id.equals(listing.getId());
    }
    
    /**
     * The hashCode of the Airbnb listing is now dependent on its id.
     * @return The hash code.
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37 * result + id.hashCode();
        return result;
    }
}
