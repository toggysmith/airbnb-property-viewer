// @TODO: Refactor class

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.HashMap;

import javafx.scene.layout.AnchorPane;

import java.util.Arrays;

public class PieChartView extends Stage
{
    PieChartController controller;
    HashMap<String,Integer> pieValues;

    public AnchorPane setUpPieChart() throws java.io.IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pieChart.fxml"));
        AnchorPane pane = loader.load();
        controller = loader.getController();    
        return pane;
    }
    
    public void populatePieChart(int[] values, String symbol)
    {
        pieValues = new HashMap<String,Integer>(); 
        int min = ListingProcessor.getMin(values);              
        int max = ListingProcessor.getMax(values);            
       
        int stepAmount;
     
        if(values.length < 10){
            pieValues.put(symbol + " " + min + " =< x < " + symbol  + " " + max, values.length);
            controller.setup(pieValues);
            return;
        }
     
        if((max - min) < 10){
            stepAmount = 1;
        }else{
        stepAmount = (max - min) / 10;
        }
    
        for(int i = min; min <= max; min += stepAmount){
            int toValue = min + stepAmount;
         
            int totalValue = (int) ListingProcessor.retrieveSpeciedAmount(values,min, toValue);
            pieValues.put(symbol + " " + min + " =< x < " + symbol + " " + toValue,totalValue);
        }  

        controller.setup(pieValues);
    }
}
