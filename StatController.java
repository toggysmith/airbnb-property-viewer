// @TODO: Refactor class

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import java.util.ArrayList;

import javafx.scene.control.*;
import java.util.*;
import java.util.HashMap;
import javafx.event.*;
import java.lang.Math;
import java.util.stream.Collectors;

/**
 * Write a description of class StatController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatController extends Controller
{
    @FXML public Button lButton1;
    @FXML public Button rButton1;
    @FXML public Button lButton2;
    @FXML public Button rButton2;
    @FXML public Button lButton3;
    @FXML public Button rButton3;
    @FXML public Button lButton4;
    @FXML public Button rButton4;
    
    @FXML public Label label1;
    @FXML public Label label2;
    @FXML public Label label3;
    @FXML public Label label4;
   
    @FXML public Label statLabel1;
    @FXML public Label statLabel2;
    @FXML public Label statLabel3;
    @FXML public Label statLabel4;
   
    //These will be the title of the statistics shown
    String stat1 = "Average Number of reviews per property:"; 
    String stat2 = "Total number of available properties:";
    String stat3 = "Number of entire home and apartments:";   
    String stat4 = "Borough with Most Expensive Borough:";
    String stat5 = "Borough with Highest Social Score";
    String stat6 = "Lowest Crime Rate";
    String stat7 = "stat7";
    String stat8 = "stat8";
    
    //Labels that contain the actual statistic
    String value1, value2, value3, value4, value5, value6, value7, value8;
    
    //Creating a list so that I could iterate through them
    private List<Label>  labelList = new ArrayList<Label>();
    
    //Declaring the double sided queue
    Deque<String> dq;
    
    //Creating a hashmap that links a button to the specified labels within their gridpane
    HashMap<Button, HashMap<Label, Label>> connectObjects = new HashMap<>();
    
    //To check if the button pressed is forward or backwards
    boolean nextOrPrev;
    
    //Creating a hashmap that links the title of the statistic to the statistic
    HashMap<String, String> statOutput;
    private static List<AirbnbListing> airbnbListings;
    private static final String roomNeeded = "Entire home/apt";
    private static List<StatisticsListing> statListings;

    private MainController mainController;
    private int fromValue;
    private int toValue;
    /**
     * Initializing the view of the pane  when you first click  onto it
     */
    @FXML
    public void initialize(){
        airbnbListings = AirbnbDataLoader.getListings();
        statListings = StatisticsLoader.getStatListings();

        mainController = (MainController) ContentContainerManager.getController(MainController.class);
        
        assignObject();
        setUpValues();
        setupHash();  
        setupQueue();
        startStats();
    }
    
    private void updateValues()
    {
        
        fromValue = mainController.getRangeValues().getFromValue();
        toValue = mainController.getRangeValues().getToValue();
        
    }
    
    /**
     * Assigning the value of the statistic to the specific string
     */
    private void setUpValues()
    {
        //set up values to corresponding function
        value1 = String.valueOf(averagePropertyView());
        value2 = String.valueOf(totalAvailableProperties());
        value3 = String.valueOf(nonPrivateRoom());
        value4 = expensiveNeighbourhood();
        value5 = socialScore();
        value6 = highestCrime();
    }
    
    /**
     * Called when the forward button is pressed
     * Allows me to get the variable name of the button pressed which is used to access the
     *     hashmap and change the relevant labels
     */
    @FXML
    private void nextStat(ActionEvent event) {
        nextOrPrev = true;
        Button b =  (Button) event.getSource();
        changeLabels(b, nextOrPrev);
    }
     
    /**
     * To change labels I iterate through the nested hashmap created, using the button parameter
     * to find the child hashmap which links that button to the two labels.
     * Depending on which button on the specific gridpane pressed, that end of the queue is taken off
     * and then assigned to the labels
     */
    private void changeLabels(Button clickedButton, boolean checkState) {
        for(Map.Entry<Button, HashMap<Label, Label>> seeSet :  connectObjects.entrySet()) {
            Button seeButton = seeSet.getKey();
               for (Map.Entry<Label, Label>  seeLabels : seeSet.getValue().entrySet()) {
                    if(seeButton.equals(clickedButton) && checkState == false) {
                        //if the forward button is pressed
                        dq.addFirst(seeLabels.getKey().getText());
                        String nextString = dq.removeLast();
                        seeLabels.getKey().setText(nextString);
                        seeLabels.getValue().setText(statOutput.get(nextString));
                    } else if (seeButton.equals(clickedButton) && checkState == true){
                        //if the backward  button is pressed
                        dq.addLast(seeLabels.getKey().getText());
                        String prevString = dq.removeFirst();
                        seeLabels.getKey().setText(prevString);
                        seeLabels.getValue().setText(statOutput.get(prevString));
                    }
                }     
            }
    }
    
    /**
     * Called when the backward button is pressed
     * Allows me to get the variable name of the button pressed which is used to access the
     *     hashmap and change the relevant labels
     */
    @FXML
    private void prevStat(ActionEvent event) {
        nextOrPrev = false;
        Button b =  (Button) event.getSource();
        changeLabels(b, nextOrPrev);
    }
    
    /**
     * Populating the label list so I wouldn't need to repeat code when initializing them when
     * the panel is accessed
     */
    private void addLabelList() {
        labelList.add(label1);
        labelList.add(label2);
        labelList.add(label3);
        labelList.add(label4);
    }
    
    /**
     * The starting statistics shown when accessing the panel
     * The first 4 values in the queue is popped, each time I do this I get the strings associated
     * with the popped values
     * I then access the  nested  hashmap and use the (i) value in labellist to find  the key in the
     * child hashmap and then set the labels to the values in that  hashmap
     */
    private void startStats() {
        addLabelList();
        for (int i = 0; i < 4; i++) {
            String labelPop = dq.pollFirst();
            String statPop = statOutput.get(labelPop);
            
            Label babel = labelList.get(i);
            for(Map.Entry<Button, HashMap<Label, Label>> seeSet :  connectObjects.entrySet()) {
                for (Map.Entry<Label, Label>  seeLabels : seeSet.getValue().entrySet()) {
                    if(seeLabels.getKey().equals(babel)) {
                    seeLabels.getKey().setText(labelPop);
                    seeLabels.getValue().setText(statPop);
                    }
                    
                }
            }
            
            
        }
    }
    
    private void setupQueue() {
        //Setting up my queue, first 4 values will be removed when initializing the pane
        dq = new ArrayDeque<String>();
        dq.addLast(stat1);
        dq.addLast(stat2);
        dq.addLast(stat3);
        dq.addLast(stat4);
        dq.addLast(stat5);
        dq.addLast(stat6);
        dq.addLast(stat7);
        dq.addLast(stat8);
    }
    
    /**
     * Setting up the hashmap that contains the title of the statistic and the statistic itself
     */
    private void setupHash() {
        statOutput = new HashMap<String, String>();
        statOutput.put(stat1, value1);
        statOutput.put(stat2, value2);
        statOutput.put(stat3, value3);
        statOutput.put(stat4, value4);
        statOutput.put(stat5, value5);
        statOutput.put(stat6, value6);
        statOutput.put(stat7, value7);
        statOutput.put(stat8, value8);
    }
    
    
    /**
     * Setting up the nested hashmap that links each button to the  two labels within the gridpane
     */
    private void assignObject() {
        nestedHash(lButton1, label1, statLabel1);
        nestedHash(rButton1, label1, statLabel1);
        nestedHash(lButton2, label2, statLabel2);
        nestedHash(rButton2, label2, statLabel2);
        nestedHash(lButton3, label3, statLabel3);
        nestedHash(rButton3, label3, statLabel3);
        nestedHash(lButton4, label4, statLabel4);
        nestedHash(rButton4, label4, statLabel4);
    }
    
    /**
     * Populates the nested hashmap
     */
    private void nestedHash(Button button, Label label, Label statLabel) {
        HashMap<Label, Label> childMap = connectObjects.get(button);
        if(childMap == null) {
            connectObjects.put(button, childMap = new HashMap<>());
        }
        childMap.put(label, statLabel);
    }
    
    /**
     * returns the average number of reviews per property
     * (statistic 1)
     */
    public int averagePropertyView() {
         int average = airbnbListings.stream()
                       .mapToInt(listing -> listing.getNumberOfReviews())
                       
                       .sum();
         long count = airbnbListings.stream()
                     .count();
        int l = (int)count;
                     return average/l;
                     
    }
    
    /**
     * returns the total available properties
     * (statistic 2)
     */
    public int totalAvailableProperties() { //int lower, int upper
        long available = airbnbListings.stream()
                    .filter(listing -> listing.getAvailability365() > 0) //&& listing.getPrice() >= lower && listing.getPrice() <= upper)
                    .count();
        int total = (int)available;
        return total;
    }
    
    /**
     * returns the number of non-private rooms
     * (statistic 3)
     */
    public int nonPrivateRoom() { //int lower, int upper
        long nonPrivate = airbnbListings.stream()
                                        .filter(listing -> listing.getRoom_type().equals(roomNeeded)) //&& listing.getPrice() >= lower && listing.getPrice() <= upper)
                                        .count();
                                        
        int privateCount = (int)nonPrivate;
        return privateCount;
    }
    

    /**
     * returns the most expensive neighbourhood
     * (statistic 3)
     */
    public String expensiveNeighbourhood() {
        
        int max = 0;
        int price= 0;
        int nights = 0;
        for(AirbnbListing x: AirbnbDataLoader.getListings()) {
            int calcPrice = x.getPrice() * x.getMinimumNights();
            if(calcPrice > max){
                max = calcPrice;
                price = x.getPrice();
                nights = x.getMinimumNights();
            }
            
        }
        final int finalPrice = price;
        final int finalNight = nights;
        
        ArrayList<AirbnbListing> abnb = new ArrayList<>();
        abnb = airbnbListings.stream()
                             .filter(listing -> listing.getPrice() == finalPrice && listing.getMinimumNights() == finalNight)
                             .collect(Collectors.toCollection(ArrayList::new));
        
                return abnb.get(0).getNeighbourhood();
    }
    
    public HashSet<String> sortAirbnbNeighbourhood() {
        return airbnbListings.stream()
                             .map(listings -> listings.getNeighbourhood())
                             .collect(Collectors.toCollection(HashSet::new));
    }
    
    public HashSet<String> sortLondonNeighbourhood() {
        return statListings.stream()
                             .map(statListings -> statListings.getBoroughName())
                             .collect(Collectors.toCollection(HashSet::new));
    }

    public String socialScore() {
        double highestSocial = 0;
        String boroughSocial = "";
        for(StatisticsListing sScore : StatisticsLoader.getStatListings()) {
            double maxSocial = sScore.getAvgTransportAccess() + sScore.getLifeSatisfaction() +
                               sScore.getWorthwileScore() + sScore.getHappinessScore() -
                               sScore.getAnxietyScore();
            if(maxSocial > highestSocial) {
                highestSocial = maxSocial;
                boroughSocial = sScore.getBoroughName() + ": " + Math.round(highestSocial) + "/30";
            }
        }
        return boroughSocial;
    }
    
    public String highestCrime() {
        double lowCrime = 1000;
        String boroughCrime = "";
        for(StatisticsListing sScore : StatisticsLoader.getStatListings()) {
            double checkCrime = sScore.getCrimeRate() ;
            if(checkCrime < lowCrime) {
                lowCrime = checkCrime;
                boroughCrime = sScore.getBoroughName();
            }
    }
    return boroughCrime;
}
}
