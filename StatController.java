
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javafx.scene.control.*;
import java.util.*;
import java.util.HashMap;
import javafx.event.*;
import java.lang.Math;
import java.util.stream.Collectors;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

/**
 * StatController extends Controller and provides the GUI and calculation of 
 * the statistics pane
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class StatController extends Controller
{

    @FXML public GridPane gridPane1;

    //These will be the title of the statistics shown
    private final String stat1 = "Average Number of reviews per property:"; 
    private final String stat2 = "Total number of available properties:";
    private final String stat3 = "Number of entire home and apartments:";   
    private final String stat4 = "Borough with Most Expensive Property:";
    private final String stat5 = "Borough with Highest Social Score";
    private final String stat6 = "Lowest Crime Rate";
    private final String stat7 = "Closest 5 Pubs";
    private final String stat8 = "Closest 5 Attractions";

    //Strings that contain the actual statistic to be shown
    String value1, value2, value3, value4, value5, value6, value7, value8;

    //Declaring the double sided queue
    public static Deque<stat> dq;

    private static List<AirbnbListing> airbnbListings;
    private static final String roomNeeded = "Entire home/apt";
    private static List<StatisticsListing> statListings;

    //To be able to round statistics to  2 decimal places
    private static final DecimalFormat df = new DecimalFormat("0.00");

    
    //These two values will store the combobox prices when the user selects a price range
    private int fromValue;
    private int toValue;

    //Declared as a class type that houses the components and values to be shown on the gridpane
    private stat avgProperties;
    private stat totalProperties;
    private stat noNonPrivate;
    private stat mostExpensive;
    private stat highSocial;
    private stat lowCrime;
    private interactiveStat pubs;
    private interactiveStat attractions;

    private ListingProcessor sortList;
    /**
     * Initializing the view of the pane  when you first click  onto it
     */
    @FXML
    public void initialize(){
        airbnbListings = AirbnbDataLoader.getListings();
        statListings = StatisticsLoader.getStatListings();

        
        setUpStats();
        setUpValues();

        setupQueue();
        startStats();
    }

    /**
     * This method is called to update the statistics to reflect the change in price range.
     */
    public void updateValues()
    {
        if(MainWindow.getMainWindow().getMainController().getRangeValues() == null )
        {
            return;
        }
        fromValue = MainWindow.getMainWindow().getMainController().getRangeValues().getFromValue();
        toValue = MainWindow.getMainWindow().getMainController().getRangeValues().getToValue();
        setUpValues();
        setUpBoxes();
    }

    /*
     * Assigning the value of the statistic to the specific function it correlates to
     * and updating them
     */
    private void setUpValues()
    {

        //set up values to corresponding function
        value1 = String.valueOf(averagePropertyView());
        value2 = String.valueOf(totalAvailableProperties());
        value3 = String.valueOf(nonPrivateRoom());
        value4 = expensiveNeighbourhood();
        value5 = socialScore();
        value6 = lowestCrime();

        avgProperties.updateValue(value1);
        totalProperties.updateValue(value2);
        noNonPrivate.updateValue(value3);
        mostExpensive.updateValue(value4);
        highSocial.updateValue(value5);
        lowCrime.updateValue(value6);

    }

    /*
     * Updates the comboboxes for the two statistics that require user interaction
     */
    private void setUpBoxes()
    {
        pubs.updateComboValues();
        attractions.updateComboValues();
    }

    /*
     * The first four statistics are added to the gridpane in the specific cell labelled
     */
    private void startStats() 
    {
        gridPane1.add(avgProperties, 0,0);
        gridPane1.add(noNonPrivate,1,0);
        gridPane1.add(totalProperties,0,1);
        gridPane1.add(mostExpensive,1,1);
    }

    /*
     * A double-sided queue is used to reflect the order of the buttons that the user presses.
     * If the right button (>) is clicked twice and the left button (<) is clicked twice  
     * then you will return to the original statistic that was shown
     */
    private void setupQueue()
    {

        dq = new ArrayDeque<stat>();
        dq.addLast(highSocial);
        dq.addLast(lowCrime);
        dq.addLast(pubs);
        dq.addLast(attractions);
    }

    /*
     * Setting up the stat class with the needed components and corresponding statistic
     */
    private void setUpStats()
    {
        avgProperties = new stat(new BorderPane(),new Label(), new Label(), stat1);
        totalProperties = new stat(new BorderPane(),new Label(), new Label(), stat2);
        noNonPrivate = new stat(new BorderPane(),new Label(), new Label(), stat3);
        mostExpensive = new stat(new BorderPane(),new Label(), new Label(), stat4);
        highSocial = new stat(new BorderPane(),new Label(), new Label(), stat5 );
        lowCrime = new stat(new BorderPane(),new Label(), new Label(), stat6);
        pubs = new interactiveStat(new BorderPane(),new Label(), new Label(), stat7,  DestinationType.PUB);
        attractions = new interactiveStat(new BorderPane(),new Label(), new Label(), stat8, DestinationType.ATTRACTION);
    }

    /*
     * Filters the list to only include properties from that price range
     */
    private List<AirbnbListing> filterPrice(List<AirbnbListing> unfilteredList)
    {
        return unfilteredList.stream()
        .filter(listing -> listing.getPrice() >= fromValue && listing.getPrice() <= toValue)
        .collect(Collectors.toList());                            

    }

    /**
     * Returns the average number of reviews per property within the price range
     * (statistic 1)
     */
    public String averagePropertyView() {

        
        
        int average = sortList.getNumberOfReviews(airbnbListings,fromValue, toValue);
        long count = sortList.getNumberofListings(airbnbListings,fromValue, toValue);
        double l = (double)count;
        //Need a try-catch as initially the program will try to divide zero by zero
        try {
            return df.format(average/l);

        } catch (Exception e) {
            return null;          
        }
    }

    /**
     * returns the number of non-private rooms  within the price range
     * (statistic 2)
     */
    public int nonPrivateRoom() {
        long nonPrivate = sortList.getNonPrivate(airbnbListings, roomNeeded, fromValue, toValue);

        int privateCount = (int)nonPrivate;
        return privateCount;
    }
    
    /**
     * returns the total available properties within the price range
     * (statistic 3)
     */
    public int totalAvailableProperties() { 
        long available = sortList.getTotalAvailability(airbnbListings,fromValue, toValue);
        int total = (int)available;
        return total;
    }

    

    /**
     * returns the most expensive neighbourhood within that price range
     * (statistic 4)
     */
    public String expensiveNeighbourhood() {

        int max = 0;

        String correctNeighbourhood = "";
        List<AirbnbListing> filtered = sortList.filterByPriceRange(airbnbListings, fromValue, toValue);
        for(int i = 0; i < filtered.size(); i++) {

            int calcPrice = filtered.get(i).getPrice() * filtered.get(i).getMinimumNights();
            if(calcPrice > max){
                max = calcPrice;
                correctNeighbourhood = filtered.get(i).getNeighbourhood();
            }

        }

        return correctNeighbourhood;
    }

    /**
     * returns the highest social score with the borough name
     * social score is calculated using a csv file taken from the government website
     * (statistic 8)
     */
    public String socialScore() {
        double highestSocial = 0;
        String boroughSocial = "";
        List<AirbnbListing> filtered = sortList.filterByPriceRange(airbnbListings, fromValue, toValue);
        
        List<String> boroughListing = new ArrayList<>();
        
        boroughListing = sortList.getBoroughs(filtered);
        for(int x = 0; x < boroughListing.size(); x++ ) {
            for(StatisticsListing sScore : StatisticsLoader.getStatListings()) {
                if(boroughListing.get(x).equals(sScore.getBoroughName())) {
                    double maxSocial = sScore.getAvgTransportAccess() + sScore.getLifeSatisfaction() +
                        sScore.getWorthwileScore() + sScore.getHappinessScore() -
                        sScore.getAnxietyScore();
                    if(maxSocial > highestSocial) {
                        highestSocial = maxSocial;
                        boroughSocial = sScore.getBoroughName() + ": " + df.format(highestSocial);
                    }

                }
           
            } 
        }

        return boroughSocial;
    }

    /**
     * returns the borough with the lowest crime per 100,000 people within 
     * the price range
     * (statistic 7)
     */
    public String lowestCrime() {
        double lowCrime = 1000;
        String boroughCrime = "";
        List<AirbnbListing> filtered = sortList.filterByPriceRange(airbnbListings, fromValue, toValue);
        
        List<String> boroughListing = new ArrayList<>();

        boroughListing = sortList.getBoroughs(filtered);

        for(int x = 0; x < boroughListing.size(); x++ ) {
            for(StatisticsListing sScore : StatisticsLoader.getStatListings()) {
                if(boroughListing.get(x).equals(sScore.getBoroughName())) {
                    double checkCrime = sScore.getCrimeRate() ;
                    if(checkCrime < lowCrime) {
                        lowCrime = checkCrime;
                        boroughCrime = sScore.getBoroughName();
                    }
                }
            }

        }
        return boroughCrime;
    }
    /**
     * Provides the GUI for each statistic by creating a borderpane with labels that show the 
     * title and value of the statistic and buttons that allow the user to switch between each
     * one
     *
     * @author Adam Murray (K21003575)
     * @author Augusto Favero (K21059800)
     * @author Mathew Tran (K21074020)
     * @author Tony Smith (K21064940)
     * @version 1.0.0
     */
    private class stat extends BorderPane
    {
        //Creating the components for the user interface to be put in the gridpane
        protected BorderPane wrapPane;    
        private Label title;
        private Label value;
        private Button rightButton;
        private Button leftButton;
        public stat(BorderPane wrapPane,Label title, Label value, String titleText)
        {
            rightButton = new Button();
            leftButton = new Button();

            this.wrapPane = wrapPane;

            this.title = title;
            title.setText(titleText);

            this.value = value;

            wrapPane.setLeft(leftButton);
            wrapPane.setRight(rightButton);

            rightButton.setOnAction(this::clickRight);
            leftButton.setOnAction(this::clickLeft);

            wrapPane.setCenter(value);

            wrapPane.setTop(title);
            wrapPane.setAlignment(title,Pos.CENTER);
            wrapPane.setAlignment(rightButton, Pos.CENTER);
            wrapPane.setAlignment(leftButton, Pos.CENTER);

            wrapPane.setMargin(rightButton, new Insets(0,15,0,0));
            wrapPane.setMargin(leftButton, new Insets(0,0,0,15));
            wrapPane.setMargin(title, new Insets(50,50,50,50));
            leftButton.setPrefSize(52,130);
            rightButton.setPrefSize(52,130);

            rightButton.setText(">");
            leftButton.setText("<");

            //wrapPane.getChildren().setAll(value);
            this.setCenter(wrapPane);
        }

        public void updateValue(String text)
        {
            value.setText(text);
        }

        /**
         * When user clicks the right button, the cell of the borderpane to be changed is added 
         * to the queue and removed, and the next stat shown is removed from the queue and added to
         * that cell of the gridpane
         */
        protected void clickRight(ActionEvent event)
        {
            int row = gridPane1.getRowIndex(this);
            int column = gridPane1.getColumnIndex(this);
            dq.addFirst(this);
            gridPane1.getChildren().remove(this);
            stat last = dq.removeLast();
            gridPane1.add(last,column,row);
        }

        /**
         * When user clicks the left button, the cell of the borderpane to be changed is added 
         * to the queue and removed, and the next stat shown is removed from the queue and added to
         * that cell of the gridpane
         */
        protected void clickLeft(ActionEvent event)
        {
            int row = gridPane1.getRowIndex(this);
            int column = gridPane1.getColumnIndex(this);
            dq.addLast(this);
            gridPane1.getChildren().remove(this);
            stat first = dq.removeFirst();
            gridPane1.add(first,column,row);
        }

        public String getTitle()
        {
            return title.getText();
        }

        public String getValue() {
            return value.getText();
        }

        public BorderPane getWrapPane()
        {
            return wrapPane;
        }

    }

    /**
     * 
     *
     * @author Adam Murray (K21003575)
     * @author Augusto Favero (K21059800)
     * @author Mathew Tran (K21074020)
     * @author Tony Smith (K21064940)
     * @version 1.0.0
     */
    public  class interactiveStat extends stat 
    {
        private InteractiveStatController interactiveStatController = new InteractiveStatController();
        private DestinationType destinationType;
        public interactiveStat(BorderPane wrapPane, Label title, Label value, String titleText,DestinationType destinationType)
        {
            super(wrapPane,title,value,titleText);
            this.destinationType = destinationType;
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Interactive-stat-pane.fxml"));
                BorderPane contentPane = loader.load();
                interactiveStatController = loader.getController();
                wrapPane.setCenter(contentPane);
            }
            catch (Exception e)
            {
                e.printStackTrace();

                AlertManager.showTerminatingError("Unable to load interactive pane correctly");
            }

        }
        private DestinationType getDesType()
        {
            return destinationType;
        }

        private InteractiveStatController getInteractiveController()
        {
            return interactiveStatController;
        }

        public void updateComboValues()
        {
            List<AirbnbListing> filteredListings = filterPrice(airbnbListings);
            List<DestinationListing> destinations = DestinationType.getDestinations(this.getDesType());

            this.getInteractiveController().updateBoxes(filteredListings,destinations,destinationType);
        }

    }
}

