// @TODO: Refactor class

import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;

import java.util.List;
import java.util.ArrayList;

public class MapController extends Controller
{
    @FXML private AnchorPane boroughMap;
    @FXML private ScrollPane scrollPane;
    @FXML private Pane root;
    
    private List<BoroughButton> buttons;

    @FXML
    public void initialize()
    {
        scrollPane.prefWidthProperty().bind(root.widthProperty());
        scrollPane.prefHeightProperty().bind(root.heightProperty());
        
        buttons = new ArrayList<>();
    }

    public void createMap()
    {
        for (Borough borough : Borough.values())
        {
            buttons.add(new BoroughButton(borough, boroughMap));
        }
    }
    
    public void updateMap()
    {
        int fromValue = MainWindow.getMainWindow().getMainController().getRangeValues().getFromValue();
        int toValue = MainWindow.getMainWindow().getMainController().getRangeValues().getToValue();
        long noOfPropertiesInBoroughWithMost = ListingManipulator.getNoOfPropertiesInBoroughWithMost(ListingManipulator.filterByPriceRange(AirbnbDataLoader.getListings(), fromValue, toValue));
        
        for (BoroughButton button : buttons)
        {
            button.update(noOfPropertiesInBoroughWithMost);
        }
    }
    
    public Pane getMapPane()
    {
        return root;
    }
}