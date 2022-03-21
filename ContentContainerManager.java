import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for controlling the content pane currently shown in the center content container.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class ContentContainerManager
{
    /**
     * Holds the currently selected content as a child node.
     */
    private final Pane contentContainer;
    
    /**
     * Holds all the content panes in a circular list that can be moved forward and backward.
     */
    private final CircularList<PaneControllerPair> circularList;
    
    /**
     * Holds all the controllers of the content panes matched to their classes.
     */
    private final Map<Class, Controller> classControllerMap;
    
    /**
     * Initialise the instance variables; load all the content panes;
     * and set the first pane.
     */
    public ContentContainerManager(Pane contentContainer)
    {
        this.contentContainer = contentContainer;
        circularList = new CircularLinkedList<>();
        classControllerMap = new HashMap<>();
        
        // Add content panes:
        try
        {
            loadContentPane("drawing-map-pane.fxml", "welcome-pane.fxml", "map-pane.fxml", "stat-pane.fxml");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            AlertManager.showTerminatingError("Unable to load content panes.");
        }
        
        Pane firstPane = circularList.getCurrent().getPane();
        contentContainer.getChildren().setAll(firstPane);
    }
    
    /**
     * Load multiple content panes at once.
     * @param paths A list of paths to content panes.
     */
    private void loadContentPane(String ... paths) throws Exception
    {
        for (String path : paths)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            
            Pane contentPane = loader.load();
            Controller controller = loader.getController();
            circularList.add(new PaneControllerPair(contentPane, controller));
            
            contentPane.prefWidthProperty().bind(contentContainer.widthProperty());
            contentPane.prefHeightProperty().bind(contentContainer.heightProperty());
            
            addControllerToMap(controller);
        }
    }
    
    /**
     * @param controller The controller to be added to the map.
     */
    private void addControllerToMap(Controller controller)
    {
        classControllerMap.put(controller.getClass(), controller);
    }
    
    
    /**
     * Retrieve an instance of a controller from its class.
     * @param controllerClass The class of the controller wanted.
     * @return The controller of a specific class.
     */
    public Controller getController(Class controllerClass)
    {
        return classControllerMap.get(controllerClass);
    }
    
    /**
     * @return The previous pane.
     */
    public Pane getPrevious()
    {
        return circularList.getPrev().getPane();
    }
    
    /**
     * @return The next pane.
     */
    public Pane getNext()
    {
        return circularList.getNext().getPane();
    }
    
    /**
     * PaneControllerPair can hold a pane and controller, is immutable and
     * provides respective getters.
     */
    private static class PaneControllerPair
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