import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import java.util.HashMap;


public class PieChartController
{
    @FXML
    private PieChart pieChart;
    
    @FXML
    private ComboBox attributesBox;
    
    
    public void setup(HashMap<String,Integer> attributeValues){
        
        for(String eachKey : attributeValues.keySet()){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                                                     new PieChart.Data(eachKey, attributeValues.get(eachKey)));
        pieChart.setData(pieChartData);
    }
    
    }
}
