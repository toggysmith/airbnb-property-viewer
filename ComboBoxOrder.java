import javafx.scene.control.TableColumn;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Write a description of class ComboBoxSorts here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public enum ComboBoxOrder
{
    HOST_NAME_ASCENDING("Host Name (Ascending)", TableColumn.SortType.ASCENDING),
    HOST_NAME_DECENDING("Host Name (Descending)", TableColumn.SortType.DESCENDING),
    PRICE_ASCENDING("Price (Ascending)", TableColumn.SortType.ASCENDING),
    PRICE_DECENDING("Price (Descending)", TableColumn.SortType.DESCENDING),
    NUMBER_OF_REVIEWS_ASCENDING("Number of reviews (Ascending)", TableColumn.SortType.ASCENDING),
    NUMBER_OF_REVIEWS_DECENDING("Number of reviews (Descending)", TableColumn.SortType.DESCENDING);
    
    private String displayText;
    private TableColumn.SortType type;
    private Map<ComboBoxOrder, TableColumn<AirbnbListing, String>> columnMap;

    /**
     * Constructor for objects of class ComboBoxSorts
     */
    private ComboBoxOrder(String displayText, TableColumn.SortType type)
    {
        this.displayText = displayText;
        this.type = type;
    }
    
    public static List<ComboBoxOrder> getAll()
    {
        List<ComboBoxOrder> list = new ArrayList<>();
        list.add(HOST_NAME_ASCENDING);
        list.add(HOST_NAME_DECENDING);
        list.add(PRICE_ASCENDING);
        list.add(PRICE_DECENDING);
        list.add(NUMBER_OF_REVIEWS_ASCENDING);
        list.add(NUMBER_OF_REVIEWS_DECENDING);
        return list;
    }
    
    public String toString() {
        return displayText;
    }
    
    public TableColumn.SortType getOrder()
    {
        return type;
    }
    
    public TableColumn<AirbnbListing, String> getColumn(BoroughWindowController boroughWindowController)
    {
        return boroughWindowController.getColumn(this);
    }
}
