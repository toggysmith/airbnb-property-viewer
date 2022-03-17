// JavaFX
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

// Java
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * ContentContainerManager is used to control the pane currently shown in the
 * content container.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public class ContentContainerManager
{
    /**
     * A list of content pane and controller pairs.
     */
    private List<PaneControllerPair> contentPanes;
    
    /**
     * The content container that holds the currently selected content.
     */
    private Pane contentContainer;
    
    /**
     * The circular iterator keeping track of the current pane.
     */
    private CircularIterator<PaneControllerPair> circularIterator;
    
    /**
     * Initialise the instance variables; load all the content panes;
     * and set the first pane.
     */
    public ContentContainerManager(Pane contentContainer)
    {
        this.contentContainer = contentContainer;
        this.contentPanes = new ArrayList<>();
        this.circularIterator = new CircularIterator<PaneControllerPair>(contentPanes);
        
        // Add content panes:
        try
        {
            loadContentPane("welcome-pane.fxml", "map-pane.fxml");
        }
        catch (IOException ioe)
        {
            System.out.println("Error : Unable to load content panes.");
            
            ioe.printStackTrace();
        }
        
        Pane firstPane = contentPanes.get(0).getPane();
        contentContainer.getChildren().setAll(firstPane);
    }
    
    /**
     * Load multiple content panes at once.
     * 
     * @param paths A list of paths to content panes.
     */
    private void loadContentPane(String ... paths) throws IOException
    {
        for (String path : paths)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            
            Pane contentPane = loader.load();
            
            contentPanes.add(new PaneControllerPair(contentPane, loader.getController()));
            
            contentPane.prefWidthProperty().bind(contentContainer.widthProperty());
            contentPane.prefHeightProperty().bind(contentContainer.heightProperty());
        }
    }
    
    /**
     * Get a controller by class.
     * 
     * @return The controller of that class.
     */
    public Controller getController(Class controllerClass)
    {
        List<Controller> controllers = new ArrayList<>();
        
        for (PaneControllerPair contentPane : contentPanes)
        {
            if (contentPane.getController().getClass() == controllerClass)
            {
                return contentPane.getController();
            }
        }
        
        return null;
    }
    
    /**
     * Get the previous pane.
     * 
     * @param The previous pane.
     */
    public Pane getPrevious()
    {
        return circularIterator.previous().getPane();
    }
    
    /**
     * Get the next pane.
     * 
     * @param The next pane.
     */
    public Pane getNext()
    {
        return circularIterator.next().getPane();
    }
    
    /**
     * PaneControllerPair can hold a pane and controller, is immutable and
     * provides respective getters.
     */
    private class PaneControllerPair
    {
        private final Pane pane;
        private final Controller controller;
        
        public PaneControllerPair(Pane pane, Controller controller)
        {
            this.pane = pane;
            this.controller = controller;
        }
        
        public Pane getPane()
        {
            return pane;
        }
        
        public Controller getController()
        {
            return controller;
        }
    }
}