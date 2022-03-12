

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class DoublyLinkedListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DoublyLinkedListTest
{
    private DoublyLinkedList<String> myList = new DoublyLinkedList<>();
    /**
     * Default constructor for test class DoublyLinkedListTest
     */
    public DoublyLinkedListTest()
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
        myList.add("Augusto");
        myList.add("Toggy");
        myList.add("Mathew");
        myList.add("Adam");
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
    public void checkNext()
    {
        String name = myList.getNextElement("Augusto");
        
        assertEquals(name, "Toggy");
    }
    
    @Test
    public void checkPrev()
    {
        String name = myList.getPrevElement("Adam");
        
        assertEquals(name,"Mathew");
    }
    
    @Test
    public void loopNext()
    {
        String name = myList.getNextElement("Adam");
        assertEquals(name, "Augusto");
    }
    
    @Test
    public void loopPrev()
    {
        String name = myList.getPrevElement("Augusto");
        assertEquals(name,"Adam");
    }
}
