import java.util.HashMap;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

/**
 * Responsible for setting up what the pie chart displayed looks like.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class PieChartView extends Stage
{
    // Controller that handles the setting up of pie chart data
    PieChartController controller;
    
    // Hashmap containing key-value pair where the key is a string (10 =< x < 15) and the integer value pair is how many elements
    // fall within that range
    HashMap<String,Integer> pieValues;
     
    /**
     * Creates pie chart by returning the AnchorPane in which it is set up.
     * @return AnchorPane
     * @throws IOException The FXML file couldn't be loaded.
     */
    public AnchorPane setUpPieChart() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pieChart.fxml"));
        AnchorPane pane = loader.load();
        controller = loader.getController();    
        return pane;
    }
    
    /**
     * Populates the pie chart with the values from the int array values, and depending on what the data shows (either £ for the
     * prices of properties or no symbol for numbers).
     * @param values The values in the piechart.
     * @param symbol The symbol.
     */
    public void populatePieChart(int[] values, String symbol)
    {
        pieValues = new HashMap<String,Integer>();
        
        int min = ListingProcessor.getMin(values);   
        int max = ListingProcessor.getMax(values);            
        
        int stepAmount;
     
        // Not enough data therefore display a singularly filled pie chart as there is not enough data.
        if (values.length < 10)
        {
            max++;
            
            pieValues.put(String.format("%s%d =< x < %s%d", symbol, min, symbol, max), values.length);
            controller.setup(pieValues);
            
            return;
        }
     
        if ((max - min) < 10)
        {
            stepAmount = 1;
        } else {
            stepAmount = (max - min) / 10;
        }
    
        for (int i = min; min <= max; min += stepAmount)
        {
            int toValue = min + stepAmount;
            
            int totalValue = (int) ListingProcessor.retrieveSpecifiedAmount(values,min, toValue);
            
            pieValues.put(String.format("%s%d =< x < %s%d", symbol, min, symbol, toValue), totalValue);
        }  

        // Passes in the data to the controller to set up pie chart appearance.
        controller.setup(pieValues);
    }
}
