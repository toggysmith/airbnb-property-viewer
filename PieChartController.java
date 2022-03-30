import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * PieChartController class controls the creation of the PieChartData to add to the pieChart object. The PieChartData is composed of
 * a key which represents the string for an arbitrary range (£10 =< x < 20) whilst the value pair is the amount of data that is
 * present in that range.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class PieChartController
{
    @FXML
    private PieChart pieChart;
    
    /**
     * Sets up the piechart by adding to the pieChart object a set of key-value pairs which are a string to represent a label, and
     * the value which is the amount of data that should be stored in that segment of the piechart.
     * @param attributeValues The number of entities for each range.
     */
    public void setup(HashMap<String, Integer> attributeValues)
    {
        ArrayList<PieChart.Data> data = new ArrayList<PieChart.Data>();
        
        for (String eachKey : attributeValues.keySet())
        {
            data.add(new PieChart.Data(eachKey, attributeValues.get(eachKey)));    
        }
    
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
        pieChart.setData(pieChartData);
    }
}
