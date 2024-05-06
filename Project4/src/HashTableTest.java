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
        
        assertNull(h.hashSearch(3));
        
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
        
        assertNull(h.hashSearch(20));
        
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
        
        Handle[] tab = h.getTable();
        assertEquals(tab[5].getId(), 5);
        assertEquals(tab[3].getId(), 3);
        assertEquals(tab[2].getId(), 2);
        assertEquals(tab[1].getId(), 1);
        
        
        h.hashDelete(5);
        tab = h.getTable();
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
        
        assertNotNull(h.hashSearch(1));
        assertNotNull(h.hashSearch(6));
        assertNull(h.hashSearch(9));
        
        assertFuzzyEquals("Hash table expanded to 16 records",
            systemOut().getHistory());
        
        assertEquals(h.p(6, 2), 2);
        assertEquals(h.p(2, 6), 6);
        assertEquals(h.p(1, 8), 8);
        assertEquals(h.p(0, 7), 7);
        assertEquals(h.p(8, 1), 1);
        assertEquals(h.p(7, 0), 0);
        assertEquals(h.p(55, 55), 385);
        assertEquals(h.p(66, 66), 594);
        assertEquals(h.p(44, 44), 220);
        assertEquals(h.p(100, 0), 0);
        assertEquals(h.p(0, 200), 200);
        assertEquals(h.p(1000, 1000), 13000);
        
        
    }
    
    /**
     * tests everything else if it can
     */
    public void testEvenMoreStuff() {
        
        Handle z1 = new Handle(1, 1, 1);
        Handle z2 = new Handle(2, 2, 2);
        Handle z3 = new Handle(3, 3, 3);
        Handle z4 = new Handle(4, 4, 4);
        Handle z5 = new Handle(50, 50, 50);
        Handle z6 = new Handle(60, 60, 60);
        Handle z7 = new Handle(70, 70, 70);
        Handle z8 = new Handle(80, 80, 80);
        Handle z9 = new Handle(90, 90, 90);
        Handle z10 = new Handle(1000, 10, 10);
        Handle z11 = new Handle(11, 1100, 11);
        Handle z12 = new Handle(12, 12, 1200);
        Handle z13 = new Handle(1300, 1300, 13);
        Handle z14 = new Handle(14, 1400, 1400);
        Handle z15 = new Handle(1500, 1500, 1500);
        
        
        h.hashInsert(z1);
        h.hashInsert(z2);
        h.hashInsert(z3);
        h.hashInsert(z4);
        h.hashInsert(z5);
        h.hashInsert(z6);
        h.hashInsert(z7);
        h.hashInsert(z8);
        h.hashInsert(z9);
        h.hashInsert(z10);
        h.hashInsert(z11);
        h.hashInsert(z12);
        h.hashInsert(z13);
        h.hashInsert(z14);
        h.hashInsert(z15);
        
        h.dump();
        
        assertFuzzyEquals("Hash table expanded to 16 records\n"
            + "Hash table expanded to 32 records\n"
            + "Hashtable: \n"
            + "1: 1\n"
            + "2: 2\n"
            + "3: 3\n"
            + "4: 4\n"
            + "6: 70\n"
            + "8: 1000\n"
            + "10: 90\n"
            + "11: 11\n"
            + "12: 12\n"
            + "14: 14\n"
            + "16: 80\n"
            + "18: 50\n"
            + "20: 1300\n"
            + "25: 1500\n"
            + "28: 60\n"
            + "total records: 15",
            systemOut().getHistory());
        
        assertEquals(h.hashDelete(60), z6);
        assertEquals(h.hashDelete(1500), z15);
        assertEquals(h.hashDelete(1000), z10);
        assertEquals(h.hashDelete(1), z1);
        assertEquals(h.hashDelete(12), z12);
        
        assertNull(h.hashDelete(60));
        assertNull(h.hashDelete(1500));
        assertNull(h.hashDelete(1000));
        assertNull(h.hashDelete(1));
        assertNull(h.hashDelete(12));
        
        
        
    }
    
    

}
