import com.opencsv.CSVReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Objects;
import java.util.List;
import java.net.URI;
import java.io.File;
import java.net.URL;
/**
 * Loads the london-borough-profiles csv file which is to be read and used in the additional statistics
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class StatisticsLoader extends Loader
{
    
    // Holds the added statistics listings once they have been loaded from secondary memory.
     
    private static ArrayList<StatisticsListing> statListings;
    
    /**
     * Loads and saves stat listings from a CSV file. Each row in the CSV file corresponds to data
     * about a borough.
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
     * @return The statistic listings saved in main memory.
     */
    public static List<StatisticsListing> getStatListings()
    {
        if (statListings == null) load();
        
        return statListings;
    }
}