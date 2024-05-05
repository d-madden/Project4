/**
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class HashTableTest extends student.TestCase {
    
    private HashTable h;
    private Handle d;
    private Handle d2;
    private Handle d3;
    private Handle d4;
    private Handle d5;

    /**
     * sets up the test methods
     */
    public void setUp() {
        
        h = new HashTable(8);
        d = new Handle(1, 1, 1);
        d2 = new Handle(2, 2, 2);
        d3 = new Handle(3, 3, 3);
        d4 = new Handle(4, 4, 4);
        d5 = new Handle(5, 5, 5);
        
    }


    /**
     * tests the insert method of hashtable
     * 
     * @throws Exception
     * 
     * 
     */
    public void testInsert() throws Exception {
        
        assertEquals(h.getSize(), 8);
        assertEquals(h.getElements(), 0);
        
        h.hashInsert(d);
        
        assertEquals(h.hashSearch(1), d);
        
        h.hashInsert(d2);
        
        assertEquals(h.hashSearch(2), d2);
        
        h.hashInsert(d3);
        
        assertEquals(h.hashSearch(3), d3);
        
        h.hashInsert(d4);
        
        assertEquals(h.hashSearch(4), d4);
        
        assertEquals(h.getSize(), 8);
        assertEquals(h.getElements(), 4);
        
        Handle[] table = h.getTable();
        
        assertEquals(table[1], d);
        assertEquals(table[2], d2);
        assertEquals(table[3], d3);
        assertEquals(table[4], d4);
        
        h.dump();
        
        assertFuzzyEquals("Hashtable: \n"
            + "1: 1\n"
            + "2: 2\n"
            + "3: 3\n"
            + "4: 4\n"
            + "total records: 4",
            systemOut().getHistory());
        
        h.hashDelete(4);
        
        assertNull(h.hashSearch(4));
        
        h.hashDelete(3);
        
        assertNull(h.hashSearch(3));
        
        h.hashDelete(2);
        
        assertNull(h.hashSearch(2));
        
        h.hashDelete(1);
        
        assertNull(h.hashSearch(1));

        
        assertEquals(h.getSize(), 8);
        assertEquals(h.getElements(), 0);
        
        

    }
    
    /**
     * tests the insert method of hashtable
     * 
     * @throws Exception
     * 
     * 
     */
    public void testResize() throws Exception {
        
        h.hashInsert(d);
        h.hashInsert(d2);
        h.hashInsert(d3);
        h.hashInsert(d4);
        h.hashInsert(d5);
        
        assertEquals(h.getSize(), 16);
    }
    
    

}
