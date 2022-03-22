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
    private static final Map<Class, Controller> classControllerMap = new HashMap<>();

    private final Pane contentContainer;
    private final CircularList<PaneControllerPair> circularList;
    
    /**
     * Initialise the instance variables; load all the content panes;
     * and set the first pane.
     */
    public ContentContainerManager(Pane contentContainer)
    {
        this.contentContainer = contentContainer;
        circularList = new CircularLinkedList<>();
        
        // Add content panes:
        try
        {
            loadContentPane("welcome-pane.fxml", "map-pane.fxml", "stat-pane.fxml", "drawable-search-area-map.fxml");
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
     * Retrieve an instance of a controller from its class.
     * @param controllerClass The class of the controller wanted.
     * @return The controller of a specific class.
     */
    public static Controller getController(Class controllerClass)
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

    private void addControllerToMap(Controller controller)
    {
        classControllerMap.put(controller.getClass(), controller);
    }

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