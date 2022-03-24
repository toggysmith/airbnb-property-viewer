// @TODO: Refactor class

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

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
   
    //XML public Label statLabel1;
   // @FXML public Label statLabel2;
   // @FXML public Label statLabel3;
    //@FXML public Label statLabel4;
   
    @FXML public BorderPane borderPane1;
    @FXML public BorderPane borderPane2;
    @FXML public BorderPane borderPane3;
    @FXML public BorderPane borderPane4;

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
    
    //Labels that contain the actual statistic
    String value1, value2, value3, value4, value5, value6, value7, value8;
    
    //Creating a list so that I could iterate through them
    private List<Label>  labelList = new ArrayList<Label>();
    
    //Declaring the double sided queue
    public static Deque<stat> dq;
    
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
    
       private HashMap<Button, BorderPane> linkBorder;
    
       private stat avgProperties;
    
       private stat totalProperties;
       private stat noNonPrivate;
       private stat mostExpensive;
       private stat highSocial;
       private stat lowCrime;
       //error prone
       private stat pubs;
       private stat attractions;
    /**
     * Initializing the view of the pane  when you first click  onto it
     */
    @FXML
    public void initialize(){
        airbnbListings = AirbnbDataLoader.getListings();
        statListings = StatisticsLoader.getStatListings();

        mainController = (MainController) ContentContainerManager.getController(MainController.class);
        //assignObject();
        linkBorderPane();
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
     * The starting statistics shown when accessing the panel
     * The first 4 values in the queue is popped, each time I do this I get the strings associated
     * with the popped values
     * I then access the  nested  hashmap and use the (i) value in labellist to find  the key in the
     * child hashmap and then set the labels to the values in that  hashmap
     */
    private void startStats() {
        
       
        //borderPane1.setCenter(avgProperties);
        //borderPane2.setCenter(totalProperties);
        //borderPane3.setCenter(noNonPrivate);
        //borderPane4.setCenter(mostExpensive);
        gridPane1.add(avgProperties, 0,0);
        gridPane1.add(noNonPrivate,1,0);
        gridPane1.add(totalProperties,0,1);
        gridPane1.add(mostExpensive,1,1);
    }
    
    private void setupQueue() {
        //Setting up my queue, first 4 values will be removed when initializing the pane
        dq = new ArrayDeque<stat>();
        dq.addLast(highSocial);
        dq.addLast(lowCrime);
        //dq.addLast(pubs)
        //dq.addLast(attractions);
    }
    
    /**
     * Setting up the hashmap that contains the title of the statistic and the statistic itself
     */
    private void setupHash() {
        //statOutput = new HashMap<String, String>();
        //statOutput.put(stat1, value1);
        //statOutput.put(stat2, value2);
        //statOutput.put(stat3, value3);
        //statOutput.put(stat4, value4);
        //statOutput.put(stat5, value5);
        //statOutput.put(stat6, value6);
       // statOutput.put(stat7, value7);
        //statOutput.put(stat8, value8);
    
        
       
       
       avgProperties = new stat(new BorderPane(),new Label(), new Label(), stat1, value1);
       totalProperties = new stat(new BorderPane(),new Label(), new Label(), stat2, value2);
       noNonPrivate = new stat(new BorderPane(),new Label(), new Label(), stat3, value3);
       mostExpensive = new stat(new BorderPane(),new Label(), new Label(), stat4, value4);
       highSocial = new stat(new BorderPane(),new Label(), new Label(), stat5, value5);
       lowCrime = new stat(new BorderPane(),new Label(), new Label(), stat6, value6);
       //error prone
       //pubs = new stat(loadPane("Interactive-stat-pane"), new Label(), new Label(),stat7, "");
       //attractions = new stat(loadPane("Interactive-stat-pane"),new Label(), new Label(), stat8, "");
    }
    
    private Pane loadPane(String path)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Pane contentPane = new Pane();
        try
        {
            contentPane = loader.load();
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
            
            AlertManager.showTerminatingError("Unable to load interactive pane correctly");        }
        
        return contentPane;
    }
    
    /**
     * Setting up the nested hashmap that links each button to the  two labels within the gridpane
     
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
    */
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

private void linkBorderPane() {
        linkBorder = new HashMap<Button, BorderPane>();
        linkBorder.put(lButton1, borderPane1);
        linkBorder.put(rButton1, borderPane1);
        linkBorder.put(lButton2, borderPane2);
        linkBorder.put(rButton2, borderPane2);
        linkBorder.put(lButton3, borderPane3);
        linkBorder.put(rButton3, borderPane3);
        linkBorder.put(lButton4, borderPane4);
        linkBorder.put(rButton4, borderPane4);

    }
    
/**
 * Write a description of class StatNode here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
private class stat extends BorderPane
{
    private BorderPane wrapPane;
    private Label title;
    private Label value;
    private Button rightButton;
    private Button leftButton;
    public stat(BorderPane wrapPane, Label title, Label value, String titleText, String valueText)
    {
    rightButton = new Button();
    leftButton = new Button();
    
    this.wrapPane = wrapPane;
    
    this.title = title;
    title.setText(titleText);
    
    this.value = value;
    value.setText(valueText);
    
    wrapPane.setLeft(leftButton);
    wrapPane.setRight(rightButton);
    
    rightButton.setOnAction(this::clickRight);
    leftButton.setOnAction(this::clickLeft);
    
    wrapPane.setCenter(value);
    wrapPane.setTop(title);
    wrapPane.setAlignment(title,Pos.CENTER);
    wrapPane.setAlignment(rightButton, Pos.CENTER);
    wrapPane.setAlignment(leftButton, Pos.CENTER);
    
    wrapPane.setMargin(rightButton, new Insets(0,0,0,50));
    wrapPane.setMargin(leftButton, new Insets(0,50,0,0));
    wrapPane.setMargin(title, new Insets(10,0,0,0));
    leftButton.setPrefSize(52,130);
    rightButton.setPrefSize(52,130);
    
    rightButton.setText(">");
    leftButton.setText("<");
    
    //wrapPane.getChildren().setAll(value);
    this.setCenter(wrapPane);
    }
    
    private void clickRight(ActionEvent event)
    {
        int row = gridPane1.getRowIndex(this);
        int column = gridPane1.getColumnIndex(this);
        dq.addFirst(this);
        gridPane1.getChildren().remove(this);
        
        
        
        stat last = dq.removeLast();
        gridPane1.add(last,column,row);
    }
    
    private void clickLeft(ActionEvent event)
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
}
