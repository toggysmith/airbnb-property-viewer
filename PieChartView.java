import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.HashMap;
import javafx.application.Platform;

public class PieChartView extends Stage
{
    private Scene scene;
    
    public PieChartView() throws java.io.IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pieChart.fxml"));
        scene = new Scene(loader.load(), 870, 870);
        
         HashMap<String,Integer> values = new HashMap<String,Integer>();
        
        PieChartController controller = loader.getController();
        
        Platform.runLater(new Runnable() {
            public void run()
            {
                controller.setup(values);
            }
        });
        
       
        values.put("Augusto",15);
        values.put("Toggy", 20);
        values.put("Adam", 25);
        
        
        
        setScene(scene);
        show();
        
    }
}
