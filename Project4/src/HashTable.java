/**
 * Comment...
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class HashTable {

    // The hash table size will always be a power of two.
    // When hashing key value k (the ID value) h1(k) = k mod M
    // which is simply the ID mod the table size,
    // and h2(k) = (((k/M) mod (M/2)) ∗ 2) + 1.
    // The key difference from what OpenDSA describes
    // is that your hash tables must be extensible.

    private int size;
    private Handle[] hash;

    // Hash Table constructor
    public HashTable(int hashSize) {

        size = hashSize;
        hash = new Handle[size];
    }


    public void hashInsert(Handle e) {

        int id = e.getId();

        int home; // Home position for e
        int pos = home = id % size; // Init probe sequence

        for (int i = 1; hash[pos] != null; i++) {
            if (id == hash[pos].getId()) {
                System.out.println("Duplicates not allowed");
                return;
            }
            pos = (home + p(id, i)) % size; // Next on probe
        }
        hash[pos] = e;

    }

    /**
     * 
     * @param e
     * @return boolean
     */
    public boolean hashSearch(Handle e) {

        int id = e.getId();

        int home; // Home position for K
        int pos = home = id % size; // Initial position is the home slot
        for (int i = 1; (id != (hash[pos]).getId())
            && (hash[pos] != null); i++) {
            
            pos = (home + p(id, i)) % size; // Next on probe
                                                             // sequence
        }
        if (id == (hash[pos]).getId()) { // Found it

            e = hash[pos];

            return true;
        }
        else {
            return false; // K not in hash table
        }
    }


    public void hashDelete() {

        // 
    }

    /**
     * gets size of hashTable
     * 
     * @return size
     */
    public int getSize() {
        
        return size;
    }
    
    private int p(int K, int i) {
        
        return i * ((((K / size) % (size / 2)) * 2) + 1);
    }

}
