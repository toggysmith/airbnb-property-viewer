import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import java.net.URL;
import javafx.scene.layout.AnchorPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import java.util.List;

/**
 * Responsible for the creation of a WebView containing an OpenLayersMap. Also acts as a bridge between the
 * Java and HTML/CSS/JavaScript side of the project.
 * 
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class OpenLayersMap extends AnchorPane
{
    WebView webView;
    WebEngine webEngine;

    public OpenLayersMap(String address)
    {
        webView = new WebView();
        webEngine = webView.getEngine();

        URL url = getClass().getClassLoader().getResource(address);
        
        webEngine.load(url.toExternalForm());

        webView.prefWidthProperty().bind(this.widthProperty());
        webView.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().add(webView);
    }
    
    public void executeScript(String script, boolean executedBeforeLoad)
    {
        if (executedBeforeLoad)
        {
            webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>() {
                    @Override
                    public void changed(ObservableValue ov, State oldState, State newState) {
                        if (newState == State.SUCCEEDED)
                        {
                            webEngine.executeScript(script);
                        }
                    }
                }
            );
        }
        else
        {
            webEngine.executeScript(script);
        }
    }
}