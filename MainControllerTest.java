import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MainControllerTest.
 *
 * author Tony Smith (K21064940)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Adam Murray (K21003575)
 * @version 1.0
 */
public class MainControllerTest
{
    // Known facts:
    private static final int MIN_PROPERTY_PRICE = 8;
    private static final int MAX_PROPERTY_PRICE = 7000;
    
    /**
     * Default constructor for test class MainControllerTest
     */
    public MainControllerTest()
    {
        
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        
    }
    
    /**
     * Test that the correct minimum property price is returned.
     */
    @Test
    public void testGetMinPropertyPrice()
    {
        MainController mainController = new MainController();
        assertEquals(mainController.getMinPropertyPrice(), MIN_PROPERTY_PRICE);
    }
    
    /**
     * Test that the correct maximum property price is returned.
     */
    @Test
    public void testGetMaxPropertyPrice()
    {
        MainController mainController = new MainController();
        assertEquals(mainController.getMaxPropertyPrice(), MAX_PROPERTY_PRICE);
    }
}
