import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import java.util.HashMap;
import java.util.ArrayList;

public class PieChartController
{
    @FXML
    private PieChart pieChart;    
    public void setup(HashMap<String,Integer> attributeValues){
    ArrayList<PieChart.Data> data = new ArrayList<PieChart.Data>();
    for(String eachKey : attributeValues.keySet()){
        data.add(new PieChart.Data(eachKey, attributeValues.get(eachKey)));    
    }
    
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
    pieChart.setData(pieChartData);
    }
}
