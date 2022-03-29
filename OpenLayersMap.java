import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URL;

import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.layout.StackPane;

import netscape.javascript.JSObject;

/**
 * Responsible for the creation of a WebView containing a local website which itself contains an interactive
 * geographical map based which uses the OpenLayers 3 library.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class OpenLayersMap extends StackPane
{
    public enum Behaviour { DRAWING, MARKER, BOUNDARIES };
    
    private WebView webView;
    private WebEngine webEngine;
    private JsToJavaBridge jsToJavaBridge;
    
    /**
     * 
     * 
     * @param pathToHtmlFile The path to the HTML file containing the local website.
     * @param initialZoom The zoom level the map should start with.
     * @param longitude 
     * @param latitude 
     */
    public OpenLayersMap(String pathToHtmlFile, int initialZoom, double longitude, double latitude)
    {
        webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.setUserAgent("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 Chrome/44.0.2403.155 Safari/537.36");

        URL url = getClass().getClassLoader().getResource(pathToHtmlFile);
        
        webEngine.load(url.toExternalForm());
        
        addScript("core-behaviour.js");

        webView.prefWidthProperty().bind(this.widthProperty());
        webView.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().add(webView);
        executeScript(String.format("setZoom(%d)", initialZoom), true);
        executeScript(String.format("setLongLat(%f, %f)", longitude, latitude), true);
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
                    linkJsToJavaBridge();
                    break;
                case BOUNDARIES:            
                    addScript("boundaries-behaviour.js");
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
    public void linkJsToJavaBridge()
    {
        webEngine.getLoadWorker().stateProperty().addListener(
        new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            if (newValue != State.SUCCEEDED) { return; }
            jsToJavaBridge = new JsToJavaBridge();
            JSObject window = (JSObject) webEngine.executeScript("window");
            window.setMember("jsToJavaBridge", jsToJavaBridge);
        }
    }
    );
    }
    
    private void addScript(String name) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("resources/open-layers-map/" + name)));
            executeScript(content, true);
        } catch (Exception e) { }
    }
}