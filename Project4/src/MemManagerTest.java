/**
 * Comment...
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
     * @throws Exception
     * 
     */
    public void testInsert() throws Exception { 
        String[] keywords = { "Good" };
        String expected = "ID: 1729, Title: Seminar Title\n"
            + "Date: 2405231000, Length: 75, X: 15, Y: 33, Cost: 125\n"
            + "Description: This is a great seminar\n"
            + "Keywords: Good, Bad, Ugly";
        Seminar mysem = new Seminar(1729, "Seminar", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This");
        byte[] toAdd = mysem.serialize();
        System.out.println(toAdd.length);

        Handle returned = mem.insert(toAdd, toAdd.length);
        for(int i = 0; i < toAdd.length; i ++) {
            assertEquals(mem.mem[i], toAdd[i]);
        }
        
        byte[] hold = new byte[100];
        
        int size = mem.get(hold, returned);
        assertEquals(size, toAdd.length);
        
        byte[] small = new byte[2];
        small[0] = toAdd[22];
        small[1] = toAdd[20];
        Handle smallH = mem.insert(small, small.length);
        
        mem.dump();

        mem.remove(returned);
        mem.remove(smallH);

    }


    /**
     * tests the get length method
     */
    public void testGetMemLength() {
        assertEquals(mem.getMemLength(), 256);

    }

}
