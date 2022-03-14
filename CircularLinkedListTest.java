

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CircularLinkedListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CircularLinkedListTest
{
    /**
     * Default constructor for test class CircularLinkedListTest
     */
    public CircularLinkedListTest()
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
    
    @Test
    public void TestMethods()
    {
        CircularLinkedList<String> circular2 = new CircularLinkedList<String>();
        circular2.add("1");
        circular2.add("2");
        circular2.add("3");
        circular2.add("4");
        assertSame("2", circular2.getNext());
        assertSame("3", circular2.getNext());
        circular2.add("5");
        assertSame("4", circular2.getNext());
        assertSame("5", circular2.getNext());
        assertSame("1", circular2.getNext());
        assertSame("5", circular2.getPrev());
        assertSame("4", circular2.getPrev());
        assertSame("3", circular2.getPrev());
        assertSame("2", circular2.getPrev());
        assertSame("1", circular2.getPrev());
        assertSame("5", circular2.getPrev());
    }
}
