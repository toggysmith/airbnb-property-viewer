// @TODO: Refactor class

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import java.util.ArrayList;

import javafx.scene.control.*;
import java.util.*;
import java.util.HashMap;
import javafx.event.*;

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
   
    //Title label
    String stat1 = "Average Number of reviews per property:"; 
    String stat2 = "Total number of available properties:";
    String stat3 = "Number of entire home and apartments:";   
    String stat4 = "Most Expensive Borough:";
    String stat5 = "stat5";
    String stat6 = "stat6";
    String stat7 = "stat7";
    String stat8 = "stat8";
    
    //Label that will show the statistic
    String value1, value2, value3, value4, value5, value6, value7, value8;
    
    private List<Label>  labelList = new ArrayList<Label>();
    Deque<String> dq;
    
    HashMap<Button, HashMap<Label, Label>> connectObjects = new HashMap<>();
    
    boolean nextOrPrev;
    HashMap<String, String> statOutput = new HashMap<String, String>();
    
    private static List<AirbnbListing> airbnbListings;
    private static final String roomNeeded = "Entire home/apt";
    
    public void initialize() {
        airbnbListings = AirbnbDataLoader.getListings();
        
        assignObject();
        setupQueue();
        startStats();
        setupHash();       
    }
    
    private void setUpValues()
    {
        //set up value 1 to corresponding function
    }
    
    @FXML
    private void nextStat(ActionEvent event) {
        nextOrPrev = true;
        Button b =  (Button) event.getSource();
        changeLabels(b, nextOrPrev);
    }
     
    private void changeLabels(Button clickedButton, boolean checkState) {
        for(Map.Entry<Button, HashMap<Label, Label>> seeSet :  connectObjects.entrySet()) {
            Button seeButton = seeSet.getKey();
               for (Map.Entry<Label, Label>  seeLabels : seeSet.getValue().entrySet()) {
                    if(seeButton.equals(clickedButton) && checkState == true) {
                        dq.addFirst(seeLabels.getKey().getText());
                        String nextString = dq.removeLast();
                        seeLabels.getKey().setText(nextString);
                        seeLabels.getValue().setText(statOutput.get(nextString));
                    } else if (seeButton.equals(clickedButton) && checkState == false){
                        dq.addLast(seeLabels.getKey().getText());
                        String prevString = dq.removeFirst();
                        seeLabels.getKey().setText(prevString);
                        seeLabels.getValue().setText(statOutput.get(prevString));
                    }
                }     
            }
    }
    
    @FXML
    private void prevStat(ActionEvent event) {
        nextOrPrev = false;
        Button b =  (Button) event.getSource();
        changeLabels(b, nextOrPrev);
    }
    
    private void addLabelList() {
        labelList.add(label1);
        labelList.add(label2);
        labelList.add(label3);
        labelList.add(label4);
    }
    
    private void startStats() {
        addLabelList();
        for (int i = 0; i < 4; i++) {
            String labelPop = dq.pollFirst();
            String statPop = statOutput.get(labelPop);
            //labelList.get(i).setText(labelPop);
            for(Map.Entry<Button, HashMap<Label, Label>> seeSet :  connectObjects.entrySet()) {
                for (Map.Entry<Label, Label>  seeLabels : seeSet.getValue().entrySet()) {
                    seeLabels.getKey().setText(labelPop);
                    seeLabels.getValue().setText(statPop);
                }
            }
        }
    }
    
    private void setupQueue() {
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
    
    //actual data 
    private void setupHash() {
        statOutput.put(stat1, value1);
        statOutput.put(stat2, value2);
        statOutput.put(stat3, value3);
        statOutput.put(stat4, value4);
        statOutput.put(stat5, value5);
        statOutput.put(stat6, value6);
        statOutput.put(stat7, value7);
        statOutput.put(stat8, value8);
    }
    
    
    
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
    
    private void nestedHash(Button button, Label label, Label statLabel) {
        HashMap<Label, Label> childMap = connectObjects.get(button);
        if(childMap == null) {
            connectObjects.put(button, childMap = new HashMap<>());
        }
        childMap.put(label, statLabel);
    }
    
    public int averagePropertyView() {
         int average = airbnbListings.stream()
                       .mapToInt(listing -> listing.getNumberOfReviews())
                       .sum();
         long count = airbnbListings.stream()
                     .count();
        int l = (int)count;
                     return average/l;
                     
    }
    
    public int totalAvailableProperties(int lower, int upper) {
        long available = airbnbListings.stream()
                    .filter(listing -> listing.getAvailability365() > 0 && listing.getPrice() >= lower && listing.getPrice() <= upper)
                    .count();
        int total = (int)available;
        return total;
    }
    
    public int nonPrivateRoom(int lower, int upper) {
        long nonPrivate = airbnbListings.stream()
                                        .filter(listing -> listing.getRoom_type().equals(roomNeeded) && listing.getPrice() >= lower && listing.getPrice() <= upper)
                                        .count();
                                        
        int privateCount = (int)nonPrivate;
        return privateCount;
    }
    
    public ArrayList<AirbnbListing> listPrice() {
        return airbnbListings.stream()
                             .filter(listing -> listing.getPrice() > 0)
                             .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<AirbnbListing> listMinimumNights() {
        return airbnbListings.stream()
                             .filter(listing -> listing.getMinimumNights() > 0)
                             .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<AirbnbListing> expensiveNeighbourhood() {
        Iterator<AirbnbListing> it1 = listPrice().iterator();
        Iterator<AirbnbListing> it2 = listMinimumNights().iterator();
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
        
        return airbnbListings.stream()
                             .filter(listing -> listing.getPrice() == finalPrice && listing.getMinimumNights() == finalNight)
                             .collect(Collectors.toCollection(ArrayList::new));
        //Will return an arraylist with one element
    }
    
    
}
