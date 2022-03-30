import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.net.URI;
import java.net.URL;
import java.io.FileReader;
import java.io.File;

import com.opencsv.CSVReader;

/**
 * Responsible for loading the Airbnb listings and storing a copy of them in main memory.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class AirbnbDataLoader extends Loader
{
    private static ArrayList<AirbnbListing> listings;
    
    /**
     * Retrieve Airbnb listings. If this method has already been called, the listings will have been
     * saved and those will be returned. Otherwise, load them from memory.
     * @return Airbnb listings.
     */
    public static List<AirbnbListing> getListings()
    {
        if (listings == null) load();
        
        return listings;
    }
    
    /*
     * Loads and saves Airbnb listings from a CSV file. Each row in the CSV file corresponds to a single listing.
     */
    private static void load()
    {
        listings = new ArrayList<>();
        
        try
        {
            URL url = AirbnbDataLoader.class.getResource("airbnb-london.csv");
            URI uri = Objects.requireNonNull(url).toURI();
            CSVReader reader = new CSVReader(new FileReader(new File(uri).getAbsolutePath()));
            String[] line;
            
            reader.readNext(); // Skip the first row (column headers).
    
            while ((line = reader.readNext()) != null)
            {
                String id = line[0];
                String name = line[1];
                String host_id = line[2];
                String host_name = line[3];
                String neighbourhood = line[4];
                double latitude = convertDouble(line[5]);
                double longitude = convertDouble(line[6]);
                String room_type = line[7];
                int price = convertInt(line[8]);
                int minimumNights = convertInt(line[9]);
                int numberOfReviews = convertInt(line[10]);
                String lastReview = line[11];
                double reviewsPerMonth = convertDouble(line[12]);
                int calculatedHostListingsCount = convertInt(line[13]);
                int availability365 = convertInt(line[14]);
        
                listings.add(new AirbnbListing(id, name, host_id, host_name, neighbourhood, latitude, longitude,
                                               room_type, price, minimumNights, numberOfReviews, lastReview,
                                               reviewsPerMonth, calculatedHostListingsCount, availability365));
            }
        }
        catch (Exception e)
        {
            AlertManager.showTerminatingError("Unable to open Airbnb CSV file.");
        }
    }
    

}
