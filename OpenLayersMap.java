import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import java.net.URL;
import javafx.scene.layout.AnchorPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import java.util.List;
import netscape.javascript.JSObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Responsible for the creation of a WebView containing an OpenLayersMap. Also acts as a bridge between the
 * Java and HTML/CSS/JavaScript side of the project.
 * 
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class OpenLayersMap extends AnchorPane
{
    public enum Behaviour { DRAWING, MARKER };
    
    private WebView webView;
    private WebEngine webEngine;
    private JavaMarker javaMarker;

    public OpenLayersMap(String address, int zoom, double longitude, double latitude)
    {
        webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.setUserAgent("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 Chrome/44.0.2403.155 Safari/537.36");

        URL url = getClass().getClassLoader().getResource(address);
        
        webEngine.load(url.toExternalForm());
        
        addScript("core-behaviour.js");

        webView.prefWidthProperty().bind(this.widthProperty());
        webView.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().add(webView);
        executeScript(String.format("setZoom(%d)", zoom), true);
        executeScript(String.format("setLongLat(%f, %f)", longitude, latitude), true);
    }
    
    private void addScript(String name) {
        try {
        String content = new String(Files.readAllBytes(Paths.get("resources/open-layers-map/" + name)));
        executeScript(content, true);
    }
    catch (Exception e) { }
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
    
    public void addBehaviour(Behaviour ... newBehaviours) {
        for (Behaviour newBehaviour : newBehaviours) {
            switch (newBehaviour) {
                // NOTE: These script paths are relative to the HTML file, not this class file.
                case DRAWING:
                    addScript("drawing-behaviour.js");
                    break;
                case MARKER:
                    addScript("marker-behaviour.js");
                setupCallFromJavaScript();
                    break;
                default:
                    // There should be a case for every behaviour.
                    throw new RuntimeException("No case defined for given behaviour.");
            }
        }
    }
    
    /**
     * Sets up an object and gives it to javascript so 
     * javascript can call java methods in that object.
     */
    public void setupCallFromJavaScript()
    {
        webEngine.getLoadWorker().stateProperty().addListener(
        new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            if (newValue != State.SUCCEEDED) { return; }
            javaMarker = new JavaMarker();
            JSObject window = (JSObject) webEngine.executeScript("window");
            window.setMember("javaMarker", javaMarker);
        }
    }
    );
    }
}