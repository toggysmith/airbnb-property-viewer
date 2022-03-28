import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;

/**
 * This creates a new window for displaying info about 
 * the properties in a borough in the price range selected.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */ 
public class BoroughDetailsStage extends Stage
{
    private BoroughListingsPriceRangeTuple boroughListingsPriceRangeTuple;

    /**
     * Create a window and load the FXML file.
     */
    public BoroughDetailsStage(BoroughListingsPriceRangeTuple boroughListingsPriceRangeTuple) throws Exception
    {
        this.boroughListingsPriceRangeTuple = boroughListingsPriceRangeTuple;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("borough-window.fxml"));

        Scene scene = new Scene(loader.load());

        BoroughDetailsController boroughWindowController = loader.getController();
        ObservableList<AirbnbListing> listings = FXCollections.observableArrayList(boroughListingsPriceRangeTuple.getListings());
        boroughWindowController.initialise(listings, boroughListingsPriceRangeTuple.getPriceRange());

        setOnCloseRequest(e -> windowClosed());

        MainView.addToOpenWindows(scene);
        MainView.setColorMode(scene);
        setScene(scene);
        setTitle(boroughListingsPriceRangeTuple.getBorough().getName());
        show();
    }
    
    public BoroughListingsPriceRangeTuple getBoroughListingsPriceRangeTuple()
    {
        return boroughListingsPriceRangeTuple;
    }

    /**
     * This tells the borough window factory that this window has been closed.
     */
    public void windowClosed()
    {
        BoroughDetailsStageFactory.getBoroughDetailsStageFactory().boroughWindowClosed(this);
        MainView.removeFromOpenWindows(getScene());
    }
}
