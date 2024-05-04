/**
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class HandleTest extends student.TestCase {

    Handle test;

    /**
     * sets up the test methods
     */
    public void setUp() {
        test = new Handle(1, 4, 5);
    }


    /**
     * tests the getID method
     */
    public void testGetID() {

        assertEquals(test.getId(), 1);
    }


    /**
     * tests the getStart method
     */
    public void testGetStart() {

        assertEquals(test.getStart(), 4);
    }


    /**
     * tests the getLength method
     */
    public void testGetLength() {

        assertEquals(test.getLength(), 5);
    }

}
