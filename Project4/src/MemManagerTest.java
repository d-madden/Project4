import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class MemManagerTest extends student.TestCase {

    MemManager mem;

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

        assertTrue(mem.freeBlock.length == 8);

        int num = 2;
        for (int i = 0; i < mem.freeBlock.length; i++) {
            assertEquals(mem.freeBlock[i].getBegIndex(), num);
            num *= 2;

        }
        assertEquals(mem.freeBlock[mem.freeBlock.length - 1].getNext()
            .getBegIndex(), 0);

        String[] keywords = { "Good" };
        Seminar mysem = new Seminar(1729, "Seminar", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This");

        byte[] toAdd = mysem.serialize();

        System.out.println(toAdd.length);

        Handle returned = mem.insert(toAdd, toAdd.length, 1729);

        assertFalse(mem.freeBlock[mem.freeBlock.length - 1].hasNext());

        assertTrue(mem.freeBlock[mem.freeBlock.length - 2].hasNext());
        assertEquals(mem.freeBlock[mem.freeBlock.length - 2].getNext()
            .getBegIndex(), 128);

        assertTrue(mem.freeBlock[mem.freeBlock.length - 3].hasNext());
        assertEquals(mem.freeBlock[mem.freeBlock.length - 3].getNext()
            .getBegIndex(), 64);

        for (int i = 0; i < toAdd.length; i++) {
            assertEquals(mem.mem[i], toAdd[i]);
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

        String[] longKeywords = {
            "Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good, Good" };
        Seminar longMysem = new Seminar(1729, "Seminar", "2405231000", 75,
            (short)15, (short)33, 125, longKeywords, "This");
        byte[] tLong = longMysem.serialize();
        Handle tooLong = mem.insert(tLong, tLong.length, 1729);

        assertEquals(tooLong, null);

        Database test = new Database(256, 12);
        test.insert(1729, "Seminar", "2405231000", 75, (short)15, (short)33,
            125, longKeywords, "This");

    }


    /**
     * tests the get length method
     */
    public void testGetMemLength() {
        assertEquals(mem.getMemLength(), 256);

    }

}
