import student.TestCase;

/**
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class SemManagerTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     * 
     * @throws Exception
     */
    public void testMInitx() throws Exception {
        SemManager sem = new SemManager();
        assertNotNull(sem);
    }
}
