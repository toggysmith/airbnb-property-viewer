import java.util.List;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;

/**
 * Responsible for setting up and maintaining the borough buttons in the map.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class MapController extends Controller
{
    @FXML private Pane root;
    @FXML private AnchorPane boroughMap;
    @FXML private ScrollPane scrollPane;
    
    private List<BoroughButton> buttons;
    
    /*
     * Make sure the scroll pane has the same size as the root and initialize the list of buttons.
     */
    @FXML
    private void initialize()
    {
        bindScrollPaneSizeToRootSize();
        
        buttons = new ArrayList<>();
    }

    /**
     * Create the borough buttons.
     */
    public void createButtons()
    {
        for (Borough borough : Borough.values())
        {
            buttons.add(new BoroughButton(borough, boroughMap));
        }
    }
    
    /**
     * Update the borough buttons.
     */
    public void updateButtons()
    {
        int fromValue = MainWindow.getMainWindow().getMainController().getRangeValues().getFromValue();
        int toValue = MainWindow.getMainWindow().getMainController().getRangeValues().getToValue();
        
        long noOfPropertiesInBoroughWithMost = ListingProcessor.getNoOfPropertiesInBoroughWithMost(ListingProcessor.filterByPriceRange(AirbnbDataLoader.getListings(), fromValue, toValue));
        
        for (BoroughButton button : buttons)
        {
            button.update(noOfPropertiesInBoroughWithMost);
        }
    }
    
    /*
     * Bind the size of the scroll pane to the size of the root so that they are both always the same size.
     */
    private void bindScrollPaneSizeToRootSize()
    {
        scrollPane.prefWidthProperty().bind(root.widthProperty());
        scrollPane.prefHeightProperty().bind(root.heightProperty());
    }
}