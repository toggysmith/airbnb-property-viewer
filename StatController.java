import javafx.scene.control.Button;
import javafx.fxml.FXML;
import java.util.ArrayList;

import javafx.scene.control.*;
import java.util.*;
import java.util.HashMap;
import javafx.event.*;

/**
 * Write a description of class StatController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatController implements Controller
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
    
    private List<Label>  labelList = new ArrayList<>();
    Deque<String> dq;
    
    HashMap<Button, HashMap<Label, Label>> connectObjects = new HashMap<>();
    
    boolean nextOrPrev;
    HashMap<String, String> statOutput = new HashMap<String, String>();
    private List<Controller> controllers;
    
    public void setControllers(List<Controller> controllers)
    {
        this.controllers = controllers;
    }
    
    @FXML
    public void initialize() {
        setupHash();
        assignObject();
        setupQueue();
        startStats();
       
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
    
    
}
