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
    public PieChartView(int[] values)
    {
      int min = Arrays.stream(values)
                      .boxed()
                      .min(Integer::compare)
                      .get();
                      
      int max = Arrays.stream(values)
                      .boxed()
                      .max(Integer::compare)
                      .get();
                      
      pieValues = new HashMap<String,Integer>();                
      populatePieChart(min, max, values);                
    }

    public AnchorPane setUpPieChart() throws java.io.IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pieChart.fxml"));
        
        AnchorPane pane = loader.load();
        
        controller = loader.getController();
        
        //setUpComboBox();
        controller.setup(pieValues);
        return pane;
    }
    
    private void populatePieChart(int min, int max, int[] values)
    {
        int stepAmount;
        
        if( values.length < 7){
            pieValues.put("� " + "0" + " - " + max, values.length);
        }else{
           stepAmount = (max - min) / 7;
           for(int i = min; min <= max; min += stepAmount){
            int toValue = (int)retrieveSpeciedAmount(values,min, (min+stepAmount));
            pieValues.put("� " + min  + " - " + (min+stepAmount),toValue);
        } 
        }
        
        
    }
    
    private long retrieveSpeciedAmount(int[] values , int from, int to)
    {
        return Arrays.stream(values)
                      .boxed()
                      .filter(i -> i > from && i < to)
                      .count();
    }
}
