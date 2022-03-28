import java.util.List;

/**
 * Responsible for holding instances of the controllers of the content panes loaded.
 *
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0.0
 */
public class Controller
{
    /**
     * A list of controller instances. An instance is stored automatically for the controller of
     * each new content pane loaded.
     */
    protected List<Controller> controllers;
    
    /**
     * Retrieve the instance of a controller based on its class.
     * @return The controller instance.
     */
    public Controller getController(Class controllerClass)
    {
        for (Controller controller : controllers)
        {
            if (controller.getClass() == controllerClass)
            {
                return controller;
            }
        }
        
        return null; // No controller instance found.
    }
    
    /**
     * Set the list of controller instances.
     * @param The new list of controller instances.
     */
    public void setControllers(List<Controller> controllers)
    {
        this.controllers = controllers;
    }
}