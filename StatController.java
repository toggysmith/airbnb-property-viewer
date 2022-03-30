
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
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
    private final String stat5 = "Boroughs with Highest Social Score";
    private final String stat6 = "Boroughs with Lowest Crime Rate";
    private final String stat7 = "Closest 5 Pubs";
    private final String stat8 = "Closest Attractions";

    //Strings that contain the actual statistic to be shown
    String value1, value2, value3, value4, value5, value6, value7, value8;

    //Declaring the double sided queue
    public static Deque<stat> dq;

    //Lists that hold the data to be used
    private static List<AirbnbListing> airbnbListings;
    private static List<StatisticsListing> statListings;
    
    //Used to check string equality
    private static final String roomNeeded = "Entire home/apt";

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

    //The class houses methods that stream the data in the csv file to be used in the statistics
    //private ListingProcessor sortList;
    /**
     * Initializing the view of the pane when it is called
     */
    @FXML
    public void initialize(){
        airbnbListings = AirbnbDataLoader.getListings();
        statListings = StatisticsLoader.getStatListings();

        //Setting up the statistics pane
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

    /**
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

    /**
     * Updates the comboboxes for the two statistics that require user interaction
     */
    private void setUpBoxes()
    {
        pubs.updateComboValues();
        attractions.updateComboValues();
    }

    /**
     * The first four statistics are added to the gridpane in the specific cell labelled
     */
    private void startStats() 
    {
        gridPane1.add(avgProperties, 0,0);
        gridPane1.add(noNonPrivate,1,0);
        gridPane1.add(totalProperties,0,1);
        gridPane1.add(mostExpensive,1,1);
    }

    /**
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

    /**
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

    

    /**
     * @return the average number of reviews per property within the price range
     * (statistic 1)
     */
    public String averagePropertyView() {

        int average = ListingProcessor.getNumberOfReviews(airbnbListings,fromValue, toValue);
        long count = ListingProcessor.getNumberOfListings(airbnbListings,fromValue, toValue);
        //Cast to a double to be able to return a number with two decimal places when division is performed
        double l = (double)count;
        //Need a try-catch as initially the program will try to divide zero by zero
        try {
            return df.format(average/l);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return the number of non-private rooms  within the price range
     * (statistic 2)
     */
    public long nonPrivateRoom() {
        long nonPrivate = ListingProcessor.getNonPrivate(airbnbListings, roomNeeded, fromValue, toValue);
        
        return nonPrivate;
    }
    
    /**
     * @return the total available properties within the price range
     * (statistic 3)
     */
    public long totalAvailableProperties() { 
        long available = ListingProcessor.getTotalAvailableProperties(airbnbListings,fromValue, toValue);
       
        return available;
    }

    

    /**
     * @return the most expensive neighbourhood within that price range
     * (statistic 4)
     */
    public String expensiveNeighbourhood() {
        //Initializing variables
        int max = 0;
        String correctNeighbourhood = "";
        
        //Getting all the properties within the price range
        List<AirbnbListing> filtered = ListingProcessor.filterByPriceRange(airbnbListings, fromValue, toValue);
        
        //Calculating the total price for each property within the price range. If it is higher than the current highest price, then that becomes the new max
        for(int i = 0; i < filtered.size(); i++) {
            //Calculating the total price for each property in within the price range
            int calcPrice = filtered.get(i).getPrice() * filtered.get(i).getMinimumNights();
            if(calcPrice > max){
                max = calcPrice;
                correctNeighbourhood = filtered.get(i).getNeighbourhood();
            }

        }

        return correctNeighbourhood;
    }

    /**
     * @return the highest social score with the borough name
     * social score is calculated using a csv file taken from the government website
     * (statistic 8)
     */
    public String socialScore() {
        double highestSocial = 0;
        String boroughSocial = "";
        
        //Getting all the properties within the price range
        List<AirbnbListing> filtered = ListingProcessor.filterByPriceRange(airbnbListings, fromValue, toValue);
        
        List<String> boroughListing = new ArrayList<>();
        //Getting all boroughs within the price range
        boroughListing = ListingProcessor.getBoroughs(filtered);
        
        HashMap<String, Double> linkSocialScore = new HashMap<>();
        
        //This nested for loop goes through each borough within the price range, calculating the social score for each and then the borough with the highest social score is returned
        for(int x = 0; x < boroughListing.size(); x++ ) {
            for(StatisticsListing sScore : StatisticsLoader.getStatListings()) {
                if(boroughListing.get(x).equals(sScore.getBoroughName())) {
                    double maxSocial = sScore.getAvgTransportAccess() + sScore.getLifeSatisfaction() +
                        sScore.getWorthwileScore() + sScore.getHappinessScore() -
                        sScore.getAnxietyScore();
                        
                        linkSocialScore.put(sScore.getBoroughName(), maxSocial);
                    

                }
           
            } 
        }

        boroughSocial = top3Boroughs(linkSocialScore, 0, true);
        
        
        return boroughSocial;
    }

    /**
     * @return the borough with the lowest crime per 1000 people within 
     * the price range
     * (statistic 7)
     */
    public String lowestCrime() {
        
        
        String boroughCrime = "";
        List<AirbnbListing> filtered = ListingProcessor.filterByPriceRange(airbnbListings, fromValue, toValue);
        
        List<String> boroughListing = new ArrayList<>();
        //Getting all boroughs within the price range
        boroughListing = ListingProcessor.getBoroughs(filtered);

        HashMap<String, Double> linkCrime = new HashMap<>();
        
        //This nested for loop goes through each borough within the price range, checking if the crime rate is lower than the current lowest, and returning the borough with the lowest crime
        for(int x = 0; x < boroughListing.size(); x++ ) {
            for(StatisticsListing sScore : StatisticsLoader.getStatListings()) {
                if(boroughListing.get(x).equals(sScore.getBoroughName())) {
                    
                    
                    linkCrime.put(sScore.getBoroughName(), sScore.getCrimeRate());
                }
            }

        }
        boroughCrime = top3Boroughs(linkCrime, 1000, false);
        return boroughCrime;
    }
    
    /**
     * @param linkScores That is iterated over to find the highest/lowest values
     *        startValue To set up the value in the pair
     *        whichStat as only two methods use this which will determine what part of this method will be executed and returned
     * 
     * @return  top three or bottom three boroughs
     */
    public String top3Boroughs(HashMap<String, Double> linkScores, double startValue, boolean whichStat) {
        Pair<String, Double> first = new Pair<String, Double>("", startValue);
        Pair<String, Double> second = new Pair<String, Double>("", startValue);
        Pair<String, Double> third = new Pair<String, Double>("", startValue);
       
        
        for(Map.Entry<String, Double> socialSet : linkScores.entrySet()) {
             if(whichStat == true) {
               if(socialSet.getValue() > first.getValue()) {
                first = new Pair<String, Double>(socialSet.getKey(), socialSet.getValue());
                       
                } else if(socialSet.getValue() > second.getValue()) {
                second = new Pair<String, Double>(socialSet.getKey(), socialSet.getValue());
                    
                } else if(socialSet.getValue() > third.getValue()) {
                third = new Pair<String, Double>(socialSet.getKey(), socialSet.getValue());
                
                }   
  
             } else if (whichStat == false) {
                if(socialSet.getValue() < first.getValue()) {
                  first = new Pair<String, Double>(socialSet.getKey(), socialSet.getValue());
                       
                  } else if(socialSet.getValue() < second.getValue()) {
                  second = new Pair<String, Double>(socialSet.getKey(), socialSet.getValue());
                    
                  } else if(socialSet.getValue() < third.getValue()) {
                  third = new Pair<String, Double>(socialSet.getKey(), socialSet.getValue());
                
                }   
                
             }
             
            }
            
        if(whichStat) {
               return (first.getKey() + ": " + df.format(first.getValue()) + System.lineSeparator() + second.getKey() + ": " + df.format(second.getValue()) + System.lineSeparator() + third.getKey() + ": " + df.format(third.getValue())); 
        } else {
               return first.getKey() + System.lineSeparator() + second.getKey() + System.lineSeparator() + third.getKey();
        }
        
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
        /**
         * Constructor for the stat class
         * Paramaters are new borderpanes for each statistic, the labels for the title of the
         * statistic and the data associated with it and the titleText that references the name
         * of the statistic
         */
        public stat(BorderPane wrapPane,Label title, Label value, String titleText)
        {
            //Creating the buttons to be used in the borderpane
            rightButton = new Button();
            leftButton = new Button();

            this.wrapPane = wrapPane;

            this.title = title;
            //Assigning the title of the statistic
            title.setText(titleText);

            this.value = value;

            //Setting the positions of the button
            wrapPane.setLeft(leftButton);
            wrapPane.setRight(rightButton);

            //Setting the methods associated with each button
            rightButton.setOnAction(this::clickRight);
            leftButton.setOnAction(this::clickLeft);

            
            //Setting up where the components are in the borderpane and their allignment
            wrapPane.setCenter(value);
            wrapPane.setTop(title);
            wrapPane.setAlignment(title,Pos.CENTER);
            wrapPane.setAlignment(rightButton, Pos.CENTER);
            wrapPane.setAlignment(leftButton, Pos.CENTER);

            //Fixing the size of the buttons and labels
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

        /**
         * @param the value of the statistic to be displayed
         * changes the value of the statistics
         */
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
     * The interactiveStat represents an interactive statistic to show the five closest Destinations (PUBs or Tourist ATTRACTIONS) relative to the data selected from the combo boxes which is provided depending on the price range
     * selected in the main pane
     *
     * @author Adam Murray (K21003575)
     * @author Augusto Favero (K21059800)
     * @author Mathew Tran (K21074020)
     * @author Tony Smith (K21064940)
     * @version 1.0.0
     */
    public  class interactiveStat extends stat 
    {
        //interactive stat controller which handles all the interactive stat main functionality
        private InteractiveStatController interactiveStatController = new InteractiveStatController();
        //what type of destination this interactive stat shows, either PUBs or Tourist ATTRACTIONs
        private DestinationType destinationType;
        
        /**
         * Method to construct the interactive stat which is a set of labels and buttons wrapped into a border pane, extending the same structure from the stat object. A DestinationType is also passed to differentiate
         * what kind of DestinationListings this stat will show (PUB or ATTRACTION)
         */
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
        
        /*
         * returns the DestinationType for this stat
         */
        private DestinationType getDesType()
        {
            return destinationType;
        }

        /*
         * returns the InteractiveStatController that handles this stat's main functionality and user interaction
         * @return InteractiveStatController
         */
        private InteractiveStatController getInteractiveController()
        {
            return interactiveStatController;
        }

        /**
         * Method called everytime the combo boxes in the main pane are updated to update the data that is held in the interactive statistics' combo boxes
         */
        public void updateComboValues()
        {
            //list of AirbnbListings that are in the selected main pane price range
            List<AirbnbListing> filteredListings = ListingProcessor.filterByPriceRange(airbnbListings, fromValue, toValue);
            //list of destinations that are of this interactive statistics DestinationType (PUB or ATTRACTION)
            List<DestinationListing> destinations = DestinationType.getDestinations(this.getDesType());

            this.getInteractiveController().updateBoxes(filteredListings,destinations,destinationType);
        }

    }
}

