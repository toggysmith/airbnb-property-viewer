import com.opencsv.CSVReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Objects;
import java.util.List;
import java.net.URI;
import java.io.File;
import java.net.URL;
/**
 * Write a description of class StatisticLoader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatisticsLoader
{
    /**
     * Holds the Airbnb listings once they have been loaded from secondary memory.
     */
    private static ArrayList<StatisticsListing> statListings;
    
    /** 
     * Loads and saves stat listings from a CSV file. Each row in the CSV file corresponds to a single listing.
     */
    private static void load()
    {
        statListings = new ArrayList<>();
        
        try
        {
            URL url = StatisticsLoader.class.getResource("london-borough-profiles.csv");
            URI uri = Objects.requireNonNull(url).toURI();
            CSVReader reader = new CSVReader(new FileReader(new File(uri).getAbsolutePath()));
            String[] line;
            
            reader.readNext(); // Skip the first row (column headers).
    
            
            while ((line = reader.readNext()) != null)
            {
                String code = line[0];
                String boroughName = line[1];
                double popDensity = convertDouble(line[2]);
                int medIncome = convertInt(line[3]);
                double crimeRate = convertDouble(line[4]);
                double avgTransportAccess = convertDouble(line[5]);
                double lifeSatisfaction = convertDouble(line[6]);
                double worthwileScore =  convertDouble(line[7]);
                double happinessScore = convertDouble(line[8]);
                double anxietyScore = convertDouble(line[9]);
                
        
                statListings.add(new StatisticsListing(code, boroughName, popDensity, medIncome,
                crimeRate, avgTransportAccess, lifeSatisfaction, worthwileScore, happinessScore,
                anxietyScore));
            }
        }
        catch (Exception e)
        {
            AlertManager.showTerminatingError("Unable to open Statistic CSV file.");
        }
    }
    
    /**
     * These listings should only be loaded once during the program's lifetime so this method will load them if and
     * only if they haven't already been loaded and saved.
     * @return The Airbnb listings saved in main memory.
     */
    public static List<StatisticsListing> getStatListings()
    {
        if (statListings == null) load();
        
        return statListings;
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