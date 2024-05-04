import java.io.File;

/**
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class DatabaseTest extends student.TestCase {

    Database data;

    public void setUp() {
        data = new Database(256, 16);

    }


    public void testResize() throws Exception {
        String[] longKeywords = {
            "Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good" };

        String[] shortKeywords = { "hello, Time, ONE" };

        data.insert(10, "Seminar", "2405231000", 75, (short)15, (short)33,
            125, shortKeywords, "This");

        data.insert(1729, "Seminar", "2405231000", 75, (short)15, (short)33,
            125, longKeywords, "This");

    }


    /**
     * tests the insert method
     */
    public void testInsert() {

    }

}
