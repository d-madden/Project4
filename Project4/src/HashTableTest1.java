import student.TestCase;

/**
 * tests hashtable
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class HashTableTest extends TestCase {
    
    private HashTable h;
    private Handle d;
    private Handle d2;
    private Handle d3;
    private Handle d4;
    
    /**
     * sets  up
     */
    public void setUp() {
        
        h = new HashTable(4);
        d = new Handle(1, 1, 1);
        d2 = new Handle(2, 2, 2);
        d3 = new Handle(3, 3, 3);
        d4 = new Handle(4, 4, 4);
    }


    /**
     * 
     */
    public void hashTest() {
        
        assertEquals(h.getSize(), 4);
        assertEquals(h.getElements(), 0);
        
        h.hashInsert(d);
        
        assertEquals(h.hashSearch(1), d);
        
        h.hashInsert(d2);
        
        assertEquals(h.hashSearch(2), d);
        
        h.hashInsert(d3);
        
        assertEquals(h.hashSearch(3), d);
        
        h.hashInsert(d4);
        
        assertEquals(h.hashSearch(4), d);
        
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
     * 
     */
    public void hashTestTwo() {
        
    }
    
    /**
     * three
     */
    public void hashTestThree() {
        
    }

}
