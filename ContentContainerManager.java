import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

/**
 * Responsible for controlling the content pane currently shown in the content pane container. Also
 * provides a way to get a content pane's controller by specifying the controller's class.
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
    private final CircularList<Pane> circularList;
    
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
     * Initialise the instance variables, load all the content panes, and set the first pane.
     * @param contentContainer The container holding the content pane.
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
            
            AlertManager.showTerminatingError("Unable to load pane correctly");
        }
        
        Pane firstPane = circularList.getCurrent();
        contentContainer.getChildren().setAll(firstPane);
    }
    
    /**
     * @return The previous pane.
     */
    public Pane getPrevious()
    {
        return circularList.getPrev();
    }
    
    /**
     * @return The next pane.
     */
    public Pane getNext()
    {
        return circularList.getNext();
    }

    /*
     * Adds the given controller to the map.
     * @param controller The controller to be added to the map.
     */
    private void addControllerToMap(Controller controller)
    {
        classControllerMap.put(controller.getClass(), controller);
    }
    
    /*
     * Takes a list of content panes and for each one:
     * -> Load the content pane using the path to its FXML file.
     * -> Make the content pane's size always be the same size as the container it's in.
     * -> Add the content pane to the circular list.
     * -> Add the content pane's controller to the controller-class-to-controller map.
     * @param paths A list of paths to each content pane's FXML file.
     * @throws Exception Thrown if the FXML file cannot be loaded properly.
     */
    private void loadContentPane(String ... paths) throws Exception
    {
        for (String path : paths)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            
            Pane contentPane = loader.load();
            
            contentPane.prefWidthProperty().bind(contentContainer.widthProperty());
            contentPane.prefHeightProperty().bind(contentContainer.heightProperty());
            
            circularList.add(contentPane);
            
            addControllerToMap(loader.getController());
        }
    }
}