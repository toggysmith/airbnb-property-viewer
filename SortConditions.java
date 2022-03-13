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
    private TableColumn.SortType type;
    
    public SortConditions(TableColumn<AirbnbListing, String> column, TableColumn.SortType type)
    {
        this.column = column;
        this.type = type;
    }
    
    public TableColumn<AirbnbListing, String> getColumn()
    {
        return column;
    }
    
    public TableColumn.SortType getOrder()
    {
        return type;
    }
}
