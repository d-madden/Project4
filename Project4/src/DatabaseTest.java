/**
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class DatabaseTest extends student.TestCase {

    private Database data;

    /**
     * sets up the database test class
     */
    public void setUp() {
        data = new Database(256, 16);

    }


    /**
     * tests the resize for the memory manager
     * 
     * @throws Exception
     */
    public void testResize() throws Exception {
        String[] longKeywords = {
            "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good" };

        String[] shortKeywords = { "hello, Time, ONE" };

        data.insert(10, "Seminar", "2405231000", 75, 
            (short)15, (short)33, 125,
            shortKeywords, "This");

        data.insert(1729, "Seminar", "2405231000", 75, 
            (short)15, (short)33,
            125, longKeywords, "This");

    }

}
