import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class MemManagerTest extends student.TestCase {

    private MemManager mem;

    /**
     * sets up the test methods
     */
    public void setUp() {
        mem = new MemManager(256);
    }


    /**
     * tests the insert method of memory manager
     * 
     * @throws Exception
     * 
     * 
     */
    public void testInsert() throws Exception {

        assertEquals(mem.getFree().length, 8);

        int num = 2;
        for (int i = 0; i < mem.getFree().length; i++) {
            assertEquals(mem.getFree()[i].getBegIndex(), num);
            num *= 2;

        }
        assertEquals(mem.getFree()[mem.getFree().length - 1].getNext()
            .getBegIndex(), 0);

        String[] keywords = { "Good" };
        Seminar mysem = new Seminar(1729, "Seminar", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This");

        byte[] toAdd = mysem.serialize();

        System.out.println(toAdd.length);

        Handle returned = mem.insert(toAdd, toAdd.length, 1729);

        assertFalse(mem.getFree()[mem.getFree().length - 1].hasNext());

        assertTrue(mem.getFree()[mem.getFree().length - 2].hasNext());
        assertEquals(mem.getFree()[mem.getFree().length - 2].getNext()
            .getBegIndex(), 128);

        assertTrue(mem.getFree()[mem.getFree().length - 3].hasNext());
        assertEquals(mem.getFree()[mem.getFree().length - 3].getNext()
            .getBegIndex(), 64);

        for (int i = 0; i < toAdd.length; i++) {
            assertEquals(mem.getMem()[i], toAdd[i]);
        }

        byte[] hold = new byte[100];

        int size = mem.get(hold, returned);
        assertEquals(size, toAdd.length);

        byte[] small = new byte[2];
        small[0] = toAdd[22];
        small[1] = toAdd[20];
        Handle smallH = mem.insert(small, small.length, 0);

        ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent1));
        mem.dump();
        String output1 = outContent1.toString();
        assertTrue(output1.contains("Freeblock List:"));
        assertTrue(output1.contains("2: 66"));
        assertTrue(output1.contains("4: 68"));
        assertTrue(output1.contains("8: 72"));
        assertTrue(output1.contains("16: 80"));
        assertTrue(output1.contains("32: 96"));
        assertTrue(output1.contains("128: 128"));

        mem.remove(returned);
        mem.remove(smallH);

        String[] longKeywords = { "Good, Good, Good, Good, "
                + "Good, Good, Good, Good, " + "Good, Good, Good, Good, "
                + "Good, Good, Good, Good, " + "Good, Good, Good, Good, "
                + "Good, Good, Good, Good, " + "Good, Good, Good, Good, "
                + "Good, Good, Good, Good, " + "Good, Good, Good, Good, "
                + "Good, Good, Good, Good, " + "Good, Good, Good, Good, "
                + "Good, Good, Good, Good" };
        Seminar longMysem = new Seminar(1729, "Seminar", "2405231000", 75,
            (short)15, (short)33, 125, longKeywords, "This");
        byte[] tLong = longMysem.serialize();
        Handle tooLong = mem.insert(tLong, tLong.length, 1729);

        assertNotNull(tooLong);

        Database test = new Database(256, 12);
        test.insert(1729, "Seminar", "2405231000", 75, (short)15, (short)33,
            125, longKeywords, "This");

    }


    /**
     * tests the whole thing
     * 
     * @throws Exception
     */
    public void testEverything() throws Exception {
        String[] longKeywords = { "Good, Good, Good, Good, "
                + "Good, Good, Good, Good, " + "Good, Good, Good, Good, "
                + "Good, Good, Good, Good, " + "Good, Good, Good, Good, "
                + "Good, Good, Good, Good, " + "Good, Good, Good, Good, "
                + "Good, Good, Good, Good, " + "Good, Good, Good, Good, "
                + "Good, Good, Good, Good, " + "Good, Good, Good, Good, "
                + "Good, Good, Good, Good" };
        Seminar mysem = new Seminar(1, "Seminar", "2405231000", 75, (short)15,
            (short)33, 125, longKeywords, "This");
        byte[] tLong = mysem.serialize();
        Handle one = mem.insert(tLong, tLong.length, 1);

        Seminar mysem2 = new Seminar(2, "Seminar", "2405231000", 75, (short)15,
            (short)33, 125, longKeywords, "This");
        byte[] tLong2 = mysem2.serialize();
        Handle two = mem.insert(tLong2, tLong2.length, 2);

        Seminar mysem3 = new Seminar(3, "Seminar", "2405231000", 75, (short)15,
            (short)33, 125, longKeywords, "This");
        byte[] tLong3 = mysem3.serialize();
        Handle three = mem.insert(tLong3, tLong3.length, 3);

        Seminar mysem4 = new Seminar(4, "Seminar", "2405231000", 75, (short)15,
            (short)33, 125, longKeywords, "This");
        byte[] tLong4 = mysem4.serialize();
        Handle four = mem.insert(tLong4, tLong4.length, 4);

        Seminar mysem5 = new Seminar(5, "Seminar", "2405231000", 75, (short)15,
            (short)33, 125, longKeywords, "This");
        byte[] tLong5 = mysem5.serialize();
        Handle five = mem.insert(tLong5, tLong5.length, 5);

        ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent1));
        mem.dump();
        String output1 = outContent1.toString();

        assertTrue(output1.contains("Freeblock List:\n"
            + "256: 0 \n"
            + "512: 2560 \n"
            + "1024: 3072 "));

        mem.remove(five);
        mem.remove(four);
        ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent2));
        mem.dump();
        String output2 = outContent2.toString();

        assertTrue(output2.contains("Freeblock List:\n"
            + "256: 0 \n"
            + "512: 1536 \n"
            + "2048: 2048 "));

        mem.remove(three);
        //assertEquals(mem.getMem()[0], 0);

        mem.remove(one);
        assertEquals(mem.getMem()[0], 0);

        assertEquals(mem.getMem()[0], 0);

        mem.remove(two);
        ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent3));
        mem.dump();
        String output3 = outContent3.toString();

        assertTrue(output3.contains("Freeblock List:\n"
            + "256: 0 \n"
            + "4096: 0 "));

        mem.remove(one);
        ByteArrayOutputStream outContent4 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent4));
        mem.dump();
        String output4 = outContent4.toString();
        assertTrue(output4.contains("Freeblock List:\n"
            + "256: 0 \n"
            + "512: 0 \n"
            + "4096: 0 "));
    }


    /**
     * tests the get length method
     */
    public void testGetMemLength() {
        assertEquals(mem.getMemLength(), 256);

    }


    /**
     * tests the dump method
     */
    public void testDump() {

        mem.dump();

    }

}
