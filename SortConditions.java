import javafx.scene.control.TableColumn;

/**
 * Write a description of class SortConditions here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SortConditions
{
    private TableColumn<AirbnbListing, String> column;
    private boolean ascending;
    
    public SortConditions(TableColumn<AirbnbListing, String> column, boolean ascending)
    {
        this.column = column;
        this.ascending = ascending;
    }
    
    public TableColumn<AirbnbListing, String> getColumn()
    {
        return column;
    }
    
    public String getOrder()
    {
        if (ascending)
        {
            return "Ascending";
        }
        return "Descending";
    }
}
