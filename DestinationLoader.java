import java.util.ArrayList;
import com.opencsv.CSVReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Objects;
import java.util.List;
import java.net.URI;
import java.io.File;
import java.net.URL;

/**
 * DestinationLoader loads the data from the two destination csv files, tourist attractions and pub destinations. A dynamic private method loadDestinations() loads the data from the passed csv file path, since the structure of both
 * files is very similar the loading for both is done by the same method.
 * 
 * This loader is used to load the csv files only once
 *
 *@author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class DestinationLoader extends Loader
{
    public static ArrayList<DestinationListing> pubDestinations;
    public static ArrayList<DestinationListing> touristDestinations;
    
    /**
     * loadDestinations loads and processes the relevant csv file whose path is passed to this method. Each row in the csv file represents a unique DestinationListing obejct and an ArrayList of these is returned
     * @return ArrayList<DestinationListing>, an array list containing all the destiantions, for pubs there are 4098 and for attractions 163
     */
    private static ArrayList<DestinationListing> loadDestinations(String fileName, PriceType priceType)
    {
        ArrayList<DestinationListing> destinationLists = new ArrayList<DestinationListing>();
        try
        {
            
            URL url = AirbnbDataLoader.class.getResource(fileName);
            URI uri = Objects.requireNonNull(url).toURI();
            CSVReader reader = new CSVReader(new FileReader(new File(uri).getAbsolutePath()));
            String[] line;
            
            reader.readNext(); // Skip the first row (column headers).
    
            while ((line = reader.readNext()) != null)
            {
                String destinationName = line[0];
                String address1 = line[1];
                String address2 = line[2];
                String boroughName = line[4];
                
                String displayAddress;
                if(address2.equals("NA")){
                    displayAddress = address1;
                }else{
                    displayAddress = address1 + ", " +  address2;
                }
                
                double longitude = AirbnbDataLoader.convertDouble(line[12]);
                double latitude = AirbnbDataLoader.convertDouble(line[13]);
                
                //issue with csv loader which does not recognise £ symbols, the following conversion is used to re-structure the string to the same format as that from the csv files
                String ticketPrice = processPoundSymbolConversion(line[15]);
                 
                if(!priceType.checkPriceValid(ticketPrice)){
                    ticketPrice = priceType.getValidPrice();
                }
                
                destinationLists.add(new DestinationListing(destinationName,displayAddress,longitude,latitude,boroughName,ticketPrice));
            }
            
        }
        catch (Exception e)
        {
            AlertManager.showTerminatingError("Unable to open " +  fileName + " CSV file correctly.");
        }
        return destinationLists;
    }
    
    /**
     * These Pub listings should only be loaded once during the program's lifetime so this method will load them if and
     * only if they haven't already been loaded and saved.
     * @return The DestinationListing listings saved in main memory.
     */
    public static List<DestinationListing> getPubs()
    {
        if (pubDestinations == null){
            PubPrice pubPriceType = new PubPrice();
            pubDestinations = loadDestinations("Pubs-london.csv",pubPriceType);
        }
        
        return pubDestinations;
    }
    
    /**
     * These Attractions listings should only be loaded once during the program's lifetime so this method will load them if and
     * only if they haven't already been loaded and saved.
     * @return The DestinationListing listings saved in main memory.
     */
    public static List<DestinationListing> getTouristDestinations()
    {
        if(touristDestinations == null){
            AttractionPrice attractionPriceType = new AttractionPrice();
            touristDestinations = loadDestinations("TouristAttractions.csv",attractionPriceType);
        }
            
        return touristDestinations;
    }
    
    /**
     * Special character conversion , the pound symbol is not correctly recognised during data loading therefore this method re-structures the format of the passed string to the correct format as the one in the csv file
     * @return String, re-formatted price string
     */
    private static String processPoundSymbolConversion(String price)
    {
        char poundChar = 163;
        String toReturnString = new String();
        if(price.length() <= 3){
            for(int x = 0; x < price.length(); x++ ){
                toReturnString = toReturnString + poundChar;
            }
            return toReturnString;
        }else if (!price.equals("free")){
            price.trim();
            toReturnString = price.replace(price.charAt(0), poundChar);
            return toReturnString;
            
        }
        return price;
    }
}
