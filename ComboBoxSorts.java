import javafx.scene.control.TableColumn;

/**
 * Write a description of class ComboBoxSorts here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ComboBoxSorts
{
    private String displayText;
    private TableColumn<AirbnbListing, String> column;
    private TableColumn.SortType type;

    /**
     * Constructor for objects of class ComboBoxSorts
     */
    public ComboBoxSorts(String displayText, TableColumn<AirbnbListing, String> column, TableColumn.SortType type)
    {
        this.displayText = displayText;
        this.column = column;
        this.type = type;
    }
    
    public String toString() {
        return displayText;
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
