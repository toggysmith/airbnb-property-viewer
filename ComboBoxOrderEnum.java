import javafx.scene.control.TableColumn;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * This enum stores the values in the borough window ComboBox
 * and the sorting info for those values
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 14/03/2022
 */
public enum ComboBoxOrderEnum
{
    HOST_NAME_ASCENDING("Host Name (Ascending)", TableColumn.SortType.ASCENDING),
    HOST_NAME_DESCENDING("Host Name (Descending)", TableColumn.SortType.DESCENDING),
    PRICE_ASCENDING("Price (Ascending)", TableColumn.SortType.ASCENDING),
    PRICE_DESCENDING("Price (Descending)", TableColumn.SortType.DESCENDING),
    NUMBER_OF_REVIEWS_ASCENDING("Number of reviews (Ascending)", TableColumn.SortType.ASCENDING),
    NUMBER_OF_REVIEWS_DESCENDING("Number of reviews (Descending)", TableColumn.SortType.DESCENDING);
    
    //Whether the table will be sorted in ascending or descending order.
    private final TableColumn.SortType order;
    private final String displayText;//The display text.

    /**
     * Constructor for objects of class ComboBoxOrderEnum
     */
    private ComboBoxOrderEnum(String displayText, TableColumn.SortType order)
    {
        this.displayText = displayText;
        this.order = order;
    }
    
    /**
     * Returns a list of all the enums values.
     * @return A list of all the enums values.
     */
    public static List<ComboBoxOrderEnum> getAll()
    {
        List<ComboBoxOrderEnum> list = new ArrayList<>(6);
        list.add(HOST_NAME_ASCENDING);
        list.add(HOST_NAME_DESCENDING);
        list.add(PRICE_ASCENDING);
        list.add(PRICE_DESCENDING);
        list.add(NUMBER_OF_REVIEWS_ASCENDING);
        list.add(NUMBER_OF_REVIEWS_DESCENDING);
        return list;
    }
    
    /**
     * Over rides the toString method  to return text to be displayed on screen.
     * @return The display text of the enum value.
     */
    @Override
    public String toString() {
        return displayText;
    }
    
    /**
     * Returns whether to sort the table in ascending or descending order.
     * @return Whether to sort the table in ascending or descending order.
     */
    public TableColumn.SortType getOrder()
    {
        return order;
    }
}
