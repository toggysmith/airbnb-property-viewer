// JavaFX
// Project


/**
 * A class to start the program (for convenience in BlueJ).
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public class Start
{
    /**
     * Launch the JavaFX application.
     */
    public static void start()
    {
        new Thread()
        {
            @Override
            public void run() {
                javafx.application.Application.launch(App.class);
            }
        }.start();
    }

    /**
     * The program entry point.
     */
    public static void main(String[] args)
    {
        start();
    }
}