import java.util.List;

/**
 * Controller provides a setup method common to all controllers
 * that allows for the passing of references to other controllers.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public interface Controller
{
    public abstract void setControllers(List<Controller> controllers);
}