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
    /**
     * Indicates which JS scripts to attach alongside the script which contains the core behaviour
     * of the map.
     */
    public enum Behaviour { DRAWING, MARKER, BOUNDARIES };

    private WebView webView;
    private WebEngine webEngine;
    private JsToJavaBridge jsToJavaBridge;

    /**
     * Setup the WebView with the local website, bind its size to its container's size, and set the 
     * initial map properties.
     * 
     * @param pathToHtmlFile The path to the HTML file containing the local website.
     * @param initialZoom The zoom level the map should start with.
     * @param initialLongitude The longitude the map should start at.
     * @param initialLatitude The latitude the map should start at.
     */
    public OpenLayersMap(String pathToHtmlFile, int initialZoom, double initialLongitude, double initialLatitude)
    {
        setupWebView(pathToHtmlFile);
        bindWebViewSizeToContainerSize();

        executeScript(String.format("setZoom(%d)", initialZoom), true);
        executeScript(String.format("setLongLat(%f, %f)", initialLongitude, initialLatitude), true);

        this.getChildren().add(webView);
    }

    /**
     * Run a JavaScript script on the HTML webpage. Depending on whether this is executed before or after the WebView has loaded,
     * a ChangeListener could be used.
     * 
     * @param script The JS script to execute.
     * @parma executedBeforeLoaded If true, a ChangeListener is used.
     */
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

    /**
     * Add the necessary scripts to implement a given list of behaviours.
     * @param newBehaviours The list of behaviours to implement.
     */
    public void addBehaviour(Behaviour ... newBehaviours)
    {
        for (Behaviour newBehaviour : newBehaviours)
        {
            switch (newBehaviour)
            {
                // NOTE: These script paths are relative to the HTML file, not this class file.
                case DRAWING:
                    executeScriptFile("drawing-behaviour.js");
                    break;
                case MARKER:
                    executeScriptFile("marker-behaviour.js");
                    linkJsToJavaBridge();
                    break;
                case BOUNDARIES:            
                    executeScriptFile("boundaries-behaviour.js");
                    break;
                default:
                    // There should be a case for every behaviour.
                    throw new RuntimeException("No case defined for given behaviour.");
            }
        }
    }

    /*
     * Setup a bridge object and provide the JavaScript with it so that the JavaScript can call Java methods.
     */
    private void linkJsToJavaBridge()
    {
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue ov, Object oldState, Object newState) {
                    if (newState != State.SUCCEEDED) return;
                    
                    jsToJavaBridge = new JsToJavaBridge();
                    
                    JSObject window = (JSObject) webEngine.executeScript("window");
                    
                    window.setMember("jsToJavaBridge", jsToJavaBridge);
                }
            }
        );
    }

    /*
     * Create the WebView - load the local website from the HTML file and add the JS script file which contains the
     * core behaviour.
     */
    private void setupWebView(String pathToHtmlFile)
    {
        webView = new WebView();
        webEngine = webView.getEngine();

        URL url = getClass().getClassLoader().getResource(pathToHtmlFile);

        webEngine.load(url.toExternalForm());

        executeScriptFile("core-behaviour.js");
    }

    /*
     * Bind the WebView's size to its container's size so that the two are always identical. I.e. make the WebView
     * always fill its container.
     */
    private void bindWebViewSizeToContainerSize()
    {
        webView.prefWidthProperty().bind(this.widthProperty());
        webView.prefHeightProperty().bind(this.heightProperty());
    }

    /*
     * Execute the script in the given file.
     */
    private void executeScriptFile(String name)
    {
        try
        {
            String content = new String(Files.readAllBytes(Paths.get("resources/open-layers-map/" + name)));

            executeScript(content, true);
        }
        catch (Exception e)
        {
            AlertManager.showTerminatingError("Cannot load script.");
        }
    }
}