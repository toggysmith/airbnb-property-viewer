import java.util.Map;
import java.util.HashMap;

/**
 * Write a description of class PropertyWindowFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoroughWindowFactory
{
    private static BoroughWindowFactory boroughWindowFactory;
    private MainWindow mainWindow;
    private Map<BoroughPriceRange, BoroughWindow> openBoroughWindows;

    public BoroughWindowFactory()
    {
        openBoroughWindows = new HashMap<>();
        mainWindow = MainWindow.getMainWindow();
    }
    
    public static BoroughWindowFactory getBoroughWindowFactory()
    {
        if (boroughWindowFactory == null)
        {
            boroughWindowFactory = new BoroughWindowFactory();
        }
        return boroughWindowFactory;
    }
    
    public BoroughWindow newBoroughWindow(Borough borough)
    {
        PriceRange priceRange = mainWindow.getRangeValues().getPriceRange();
        BoroughPriceRange boroughPriceRange = new BoroughPriceRange(borough, priceRange);
        BoroughWindow boroughWindow = openBoroughWindows.get(boroughPriceRange);
        if (boroughWindow == null)
        {
            boroughWindow = new BoroughWindow(boroughPriceRange);
            openBoroughWindows.put(boroughPriceRange, boroughWindow);
        }
        boroughWindow.setFront();
        return boroughWindow;
    }
    
    public void boroughWindowClosed(BoroughWindow boroughWindow)
    {
        openBoroughWindows.remove(boroughWindow.getBoroughPriceRange());
    }
}
