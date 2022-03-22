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
 * Write a description of class DestinationLoader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DestinationLoader
{
    // instance variables - replace the example below with your own
    public static ArrayList<DestinationListing> pubDestinations;
    public static ArrayList<DestinationListing> touristDestinations;

    
    
    private static ArrayList<DestinationListing> loadDestinations(String fileName)
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
                    displayAddress = address2;
                }else{
                    displayAddress = address1 + ", " +  address2;
                }
                
                double longitude = convertDouble(line[12]);
                double latitude = convertDouble(line[13]);
                
                String ticketPrice = line[15];
                
                destinationLists.add(new DestinationListing(destinationName,displayAddress,longitude,latitude,boroughName,ticketPrice));
            }
            
        }
        catch (Exception e)
        {
            AlertManager.showTerminatingError("Unable to open Pubs CSV file.");
        }
        return destinationLists;
    }
    
    public static List<DestinationListing> getPubs()
    {
        if (pubDestinations == null) pubDestinations = loadDestinations("Pubs-london.csv");
        
        return pubDestinations;
    }
    
    public static List<DestinationListing> getTouristDestinations()
    {
        if(touristDestinations == null) touristDestinations = loadDestinations("TouristAttractions.csv");
            
        return touristDestinations;
    }
    
    /**
     * @param doubleString The string to be converted to Double type.
     * @return The Double value of the string, or -1.0 if the string is either empty or just whitespace.
     */
    private static Double convertDouble(String doubleString) throws Exception
    {
        if (doubleString != null && !doubleString.trim().equals(""))
        {
            return Double.parseDouble(doubleString);
        }

        return -1.0;
    }

    /**
     * @param intString The string to be converted to Integer type.
     * @return The Integer value of the string, or -1 if the string is either empty or just whitespace.
     */
    private static Integer convertInt(String intString)
    {
        if (intString != null && !intString.trim().equals(""))
        {
            return Integer.parseInt(intString);
        }

        return -1;
    }
}
