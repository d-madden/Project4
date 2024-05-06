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
        
        h.hashDelete(4);
        
        h.dump();
        
        assertFuzzyEquals("Hashtable: \n"
            + "1: 1\n"
            + "2: 2\n"
            + "3: 3\n"
            + "4: TOMBSTONE\n"
            + "total records: 3", 
            systemOut().getHistory());
        
        h.hashInsert(d5);
        
        assertEquals(h.getSize(), 8);
        
        h.hashInsert(d4);
        
        assertEquals(h.getSize(), 16);
        
        h.dump();
        
        assertFuzzyEquals("Hashtable: \n"
            + "1: 1\n"
            + "2: 2\n"
            + "3: 3\n"
            + "4: TOMBSTONE\n"
            + "total records: 3\n"
            + "Hash table expanded to 16 records\n"
            + "Hashtable: \n"
            + "1: 1\n"
            + "2: 2\n"
            + "3: 3\n"
            + "4: 4\n"
            + "5: 5\n"
            + "total records: 5", 
            systemOut().getHistory());
        
        
        
        
        
    }
    
    /**
     * tests the insert method of hashtable
     * 
     * @throws Exception
     * 
     * 
     */
    public void testTombstones() throws Exception {
        
        h.hashInsert(d);
        h.hashInsert(d2);
        h.hashInsert(d3);
        h.hashInsert(d5);
        
        h.hashDelete(5);
        Handle[] tab = h.getTable();
        assertEquals(tab[5].getId(), -1);
        
        assertEquals(tab[3].getId(), 3);
        h.hashDelete(3);
        tab = h.getTable();
        assertEquals(tab[3].getId(), -1);
        
        assertEquals(tab[2].getId(), 2);
        h.hashDelete(2);
        tab = h.getTable();
        assertEquals(tab[2].getId(), -1);
        
        assertEquals(tab[1].getId(), 1);
        h.hashDelete(1);
        tab = h.getTable();
        assertEquals(tab[1].getId(), -1);
        
        h.dump();
        
        assertFuzzyEquals("Hashtable: \n"
            + "1: TOMBSTONE\n"
            + "2: TOMBSTONE\n"
            + "3: TOMBSTONE\n"
            + "5: TOMBSTONE\n"
            + "total records: 0",
            systemOut().getHistory());
        
        assertEquals(h.getSize(), 8);
        
        assertNull(h.hashDelete(1));
        
        Handle d6 = new Handle(6, 6, 6);
        Handle d7 = new Handle(7, 7, 7);
        Handle d8 = new Handle(8, 8, 8);
        Handle d9 = new Handle(9, 9, 9);
        
        h.hashInsert(d);
        h.hashInsert(d6);
        h.hashInsert(d7);
        h.hashInsert(d8);
        h.hashInsert(d9);
        
        h.dump();
        
        assertFuzzyEquals("Hashtable: \n"
            + "1: TOMBSTONE\n"
            + "2: TOMBSTONE\n"
            + "3: TOMBSTONE\n"
            + "5: TOMBSTONE\n"
            + "total records: 0\n"
            + "Hash table expanded to 16 records\n"
            + "Hashtable: \n"
            + "1: 1\n"
            + "2: 9\n"
            + "6: 6\n"
            + "7: 7\n"
            + "8: 8\n"
            + "total records: 5",
            systemOut().getHistory());
        
        assertEquals(h.getSize(), 16);
        assertEquals(h.getElements(), 5);
        
        h.hashInsert(d6);
        
        assertFuzzyEquals("Hashtable: \n"
            + "1: TOMBSTONE\n"
            + "2: TOMBSTONE\n"
            + "3: TOMBSTONE\n"
            + "5: TOMBSTONE\n"
            + "total records: 0\n"
            + "Hash table expanded to 16 records\n"
            + "Hashtable: \n"
            + "1: 1\n"
            + "2: 9\n"
            + "6: 6\n"
            + "7: 7\n"
            + "8: 8\n"
            + "total records: 5\n"
            + "Duplicates not allowed",
            systemOut().getHistory());
        
        
    }
    
    /**
     * tests everything else if it can
     */
    public void testRemaining() {
        
        assertNull(h.hashDelete(1));
        
        Handle d6 = new Handle(6, 6, 6);
        Handle d7 = new Handle(7, 7, 7);
        Handle d8 = new Handle(8, 8, 8);
        Handle d9 = new Handle(9, 9, 9);
        
        h.hashInsert(d);
        h.hashInsert(d6);
        h.hashInsert(d7);
        h.hashInsert(d8);
        h.hashInsert(d9);
        
        assertFuzzyEquals("Hash table expanded to 16 records",
            systemOut().getHistory());
        
        System.out.println(h.p(6, 2));
        System.out.println(h.p(2, 6));
        System.out.println(h.p(1, 8));
        System.out.println(h.p(0, 7));
        System.out.println(h.p(8, 1));
        System.out.println(h.p(7, 0));
        
        assertFuzzyEquals("Hash table expanded to 16 records\n"
            + "2\n"
            + "6\n"
            + "8\n"
            + "7\n"
            + "1\n"
            + "0",
            systemOut().getHistory());
    }
    
    
    
    

}
