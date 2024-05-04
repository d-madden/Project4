/**
 * Comment...
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class FreeBlockTest extends student.TestCase {

    FreeBlock test;

    /**
     * sets up the test methods
     */
    public void setUp() {
        test = new FreeBlock(0);

    }


    /**
     * tests the getBeg index method
     */
    public void testGetBegIndex() {
        assertEquals(test.getBegIndex(), 0);
    }


    /**
     * tests the set next method
     */
    public void testSetNext() {
        assertEquals(test.getNext(), null);
        assertFalse(test.hasNext());

        FreeBlock theNext = new FreeBlock(1);

        test.setNext(theNext);

        assertTrue(test.hasNext());
        assertEquals(test.getNext(), theNext);
        assertEquals(test.getNext().getBegIndex(), 1);

        test.movePointer();
        assertFalse(test.hasNext());
        assertEquals(test.getNext(), null);

    }

}
