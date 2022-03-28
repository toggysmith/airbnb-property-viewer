

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * PieChartController class controls the creation of the PieChartData to add to the pieChart object. The PieChartData is composed of a key which represents the string for an arbitrary range (£10 =< x < 20) whilst the value pair is
 * the amount of data that is present in that range
 */
public class PieChartController
{
    @FXML
    private PieChart pieChart;
    
    /**
     * Sets up the piechart by adding to the pieChart object a set of key-value pairs which are a string to represent a label, and the value which is the amount of data that should be stored in that segment of the piechart
     */
    public void setup(HashMap<String,Integer> attributeValues)
    {
        ArrayList<PieChart.Data> data = new ArrayList<PieChart.Data>();
        for(String eachKey : attributeValues.keySet()){
            data.add(new PieChart.Data(eachKey, attributeValues.get(eachKey)));    
        }
    
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
        pieChart.setData(pieChartData);
    }
}
