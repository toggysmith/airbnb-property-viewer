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
     int min = Arrays.stream(values)
                      .boxed()
                      .min(Integer::compare)
                      .get();
                      
      int max = Arrays.stream(values)
                      .boxed()
                      .max(Integer::compare)
                      .get();              
       
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
     
        
             //String key = String.format("%s £",min, "%s  < x < £ ",max);
        //    pieValues.put("£ " + min + "< x <= £ " + max,values.length);
      //  }else{
     for(int i = min; min <= max; min += stepAmount){
         int toValue = min + stepAmount;
         
            int totalValue = (int)retrieveSpeciedAmount(values,min, toValue);
            pieValues.put(symbol + " " + min + " =< x < " + symbol + " " + toValue,totalValue);
     }  
    //}
    controller.setup(pieValues);
    }
    
    private long retrieveSpeciedAmount(int[] values , int from, int to)
    {
        return Arrays.stream(values)
                      .filter(i -> (i >= from) && (i < to))
                      .count();
    }
}
