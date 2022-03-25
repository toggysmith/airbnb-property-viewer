// @TODO: Refactor class

import javafx.scene.control.Button;
import javafx.fxml.FXML;

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

    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    
    
       
       private int fromValue;
       private int toValue;
    
       
    
       private stat avgProperties;
    
       private stat totalProperties;
       private stat noNonPrivate;
       private stat mostExpensive;
       private stat highSocial;
       private stat lowCrime;
       //error prone
       private interactiveStat pubs;
       private interactiveStat attractions;
    /**
     * Initializing the view of the pane  when you first click  onto it
     */
    @FXML
    public void initialize(){
        airbnbListings = AirbnbDataLoader.getListings();
        statListings = StatisticsLoader.getStatListings();
        
        //linkBorderPane();
        setUpStats();
        setUpValues();
        
        
        setupQueue();
        startStats();
    }
    
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
        //RESETVALUES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
    
    /**
     * Assigning the value of the statistic to the specific string
     */
    private void setUpValues()
    {
        //updateValues();
        //set up values to corresponding function
        value1 = String.valueOf(averagePropertyView());
        value2 = String.valueOf(totalAvailableProperties());
        value3 = String.valueOf(nonPrivateRoom());
        value4 = expensiveNeighbourhood();
        value5 = socialScore();
        value6 = highestCrime();
        
        avgProperties.updateValue(value1);
        totalProperties.updateValue(value2);
        noNonPrivate.updateValue(value3);
        mostExpensive.updateValue(value4);
        highSocial.updateValue(value5);
        lowCrime.updateValue(value6);
        
    }

    private void setUpBoxes()
    {
        pubs.updateComboValues();
        attractions.updateComboValues();
    }
    
    /**
     * The starting statistics shown when accessing the panel
     * The first 4 values in the queue is popped, each time I do this I get the strings associated
     * with the popped values
     * I then access the  nested  hashmap and use the (i) value in labellist to find  the key in the
     * child hashmap and then set the labels to the values in that  hashmap
     */
    private void startStats() 
    {
        gridPane1.add(avgProperties, 0,0);
        gridPane1.add(noNonPrivate,1,0);
        gridPane1.add(totalProperties,0,1);
        gridPane1.add(mostExpensive,1,1);
    }
    
    private void setupQueue()
    {
        //Setting up my queue, first 4 values will be removed when initializing the pane
        dq = new ArrayDeque<stat>();
        dq.addLast(highSocial);
        dq.addLast(lowCrime);
        dq.addLast(pubs);
        dq.addLast(attractions);
    }
    
    /**
     * Setting up the hashmap that contains the title of the statistic and the statistic itself
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
     * returns the average number of reviews per property
     * (statistic 1)
     */
    public String averagePropertyView() {
        
        List<AirbnbListing> filtered = filterPrice(airbnbListings);
        
         int average = filtered.stream()
                       .mapToInt(listing -> listing.getNumberOfReviews())
                       .sum();
         long count = filtered.stream()
                     .count();
        double l = (double)count;
        //Need a try-catch as initially the program will try to divide zero by zerp
        try {
            return df.format(average/l);
                     
            } catch (Exception e) {
              return null;          
            }
    }
    
    private List<AirbnbListing> filterPrice(List<AirbnbListing> unfilteredList)
    {
        return unfilteredList.stream()
                             .filter(listing -> listing.getPrice() >= fromValue && listing.getPrice() <= toValue)
                             .collect(Collectors.toList());                            
                      
    }
    
    /**
     * returns the total available properties
     * (statistic 2)
     */
    public int totalAvailableProperties() { //int lower, int upper
        List<AirbnbListing> filtered = filterPrice(airbnbListings);

        long available = filtered.stream()
                    .filter(listing -> listing.getAvailability365() > 0) 
                    .count();
        int total = (int)available;
        return total;
    }
    
    /**
     * returns the number of non-private rooms
     * (statistic 3)
     */
    public int nonPrivateRoom() { //int lower, int upper
        List<AirbnbListing> filtered = filterPrice(airbnbListings);

        long nonPrivate = filtered.stream()
                                        .filter(listing -> listing.getRoom_type().equals(roomNeeded)) 
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
        String correctNeighbourhood = "";
        List<AirbnbListing> filtered = filterPrice(airbnbListings);
        for(int i = 0; i < filtered.size(); i++) {
            
            int calcPrice = filtered.get(i).getPrice() * filtered.get(i).getMinimumNights();
            if(calcPrice > max){
                max = calcPrice;
                price = filtered.get(i).getPrice();
                nights = filtered.get(i).getMinimumNights();
                correctNeighbourhood = filtered.get(i).getNeighbourhood();
            }
            
        }
        /**final int finalPrice = price;
        final int finalNight = nights;
        

        ArrayList<AirbnbListing> abnb = new ArrayList<>();
        abnb = filtered.stream()
                             .filter(listing -> listing.getPrice() == finalPrice && listing.getMinimumNights() == finalNight)
                             .collect(Collectors.toCollection(ArrayList::new));
        
                return filtered.get(0).getNeighbourhood(); */
                
                return correctNeighbourhood;
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
                boroughSocial = sScore.getBoroughName() + ": " + df.format(highestSocial);
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
    
/**
 * Write a description of class StatNode here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
private class stat extends BorderPane
{
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
    
    public void updateValue(String text)
    {
        value.setText(text);
    }
    
    protected void clickRight(ActionEvent event)
    {
        int row = gridPane1.getRowIndex(this);
        int column = gridPane1.getColumnIndex(this);
        dq.addFirst(this);
        gridPane1.getChildren().remove(this);
        stat last = dq.removeLast();
        gridPane1.add(last,column,row);
    }
    
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

