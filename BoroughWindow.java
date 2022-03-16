import java.util.List;
import javafx.collections.ObservableList;

/**
 * Write a description of class BoroughWindow here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoroughWindow
{
    private BoroughWindowView boroughView;
    private ObservableList<AirbnbListing> listings;
    
    public BoroughWindow(BoroughEnum borough)
    {
        listings = MainView.getListingsInBorough(borough.NAME);
        try
        {
            boroughView = new BoroughWindowView(borough.NAME, listings);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
