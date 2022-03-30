
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.util.HashMap;
import javafx.scene.layout.AnchorPane;
import java.util.Arrays;

public class PieChartView extends Stage
{
    //controller that handles the setting up of pie chart data
    PieChartController controller;
    //hashmap containing key-value pair where the key is a string (10 =< x < 15) and the integer value pair is how many elements fall within that range
    HashMap<String,Integer> pieValues;
     
    /**
     * Creates pie chart by returning the anchorpane in which it is set up
     * @return AnchorPane
     */
    public AnchorPane setUpPieChart() throws java.io.IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pieChart.fxml"));
        AnchorPane pane = loader.load();
        controller = loader.getController();    
        return pane;
    }
    
    /**
     * Populates the pie chart with the values from the int array values, and depending on what the data shows (either £ for the prices of properties or no symbol for numbers)
     */
    public void populatePieChart(int[] values, String symbol)
    {
        pieValues = new HashMap<String,Integer>();
        //min value from array of int
        int min = ListingProcessor.getMin(values);   
        //max value from array of int
        int max = ListingProcessor.getMax(values);            
        
        int stepAmount;
     
        //not enough data therefore display a singularly filled pie chart as there is not enough data
        if(values.length < 10){
            max++;
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
         
            int totalValue = (int) ListingProcessor.retrieveSpecifiedAmount(values,min, toValue);
            pieValues.put(symbol + " " + min + " =< x < " + symbol + " " + toValue,totalValue);
        }  

        //passes in the data to the controller to set up pie chart appearance
        controller.setup(pieValues);
    }
}
