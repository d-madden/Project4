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

    private int size; // size of array
    private int elements; // number of handles
    private Handle[] hash;
    private static Handle EMPTYHANDLE;

    // Hash Table constructor
    public HashTable(int hashSize) {

        size = hashSize;
        hash = new Handle[size];
        EMPTYHANDLE = new Handle(-1, 0, 0);

        // populates the array with tombstones
        for (int i = 0; i < size; i++) {
            hash[i] = EMPTYHANDLE;
        }
    }


    public void hashInsert(Handle e) {

        int id = e.getId();

        int home; // Home position for e
        int pos = home = id % size; // Init probe sequence

        for (int i = 1; hash[pos] != EMPTYHANDLE; i++) {
            if (id == hash[pos].getId()) {
                System.out.println("Duplicates not allowed");
                return;
            }
            pos = (home + p(id, i)) % size; // Next on probe
        }
        hash[pos] = e;
        elements++;

    }


    /**
     * 
     * @param id
     * @return boolean
     */
    public Handle hashSearch(int id) {

        int home; // Home position for K
        Handle e = null;
        int pos = home = id % size; // Initial position is the home slot
        for (int i = 1; (id != (hash[pos]).getId())
            && (hash[pos] != EMPTYHANDLE); i++) {

            pos = (home + p(id, i)) % size; // Next on probe
                                            // sequence
        }
        if (id == (hash[pos]).getId()) { // Found it

            e = hash[pos];

            return e;
        }
        else {
            return e; // K not in hash table
        }
    }


    /**
     * 
     * @param e
     * @return removed handle
     */
    public Handle hashDelete(int id) {

        int home; // Home position for K
        int pos = home = id % size; // Initial position is the home slot
        Handle removed = EMPTYHANDLE;

        for (int i = 1; (id != (hash[pos]).getId())
            && (hash[pos] != EMPTYHANDLE); i++) {

            pos = (home + p(id, i)) % size; // Next on probe
                                            // sequence
        }
        if (id == (hash[pos]).getId()) { // Found it

            removed = hash[pos];
            hash[pos] = EMPTYHANDLE;
            elements--;

            return removed;
        }
        else {
            return EMPTYHANDLE; // K not in hash table
        }
    }


    /**
     * dumps hashTable
     */
    public void dump() {

        System.out.println("Hashtable: ");

        for (int i = 0; i < size; i++) {

            if (hash[i].getId() != -1) {

                System.out.println(i + ": " + hash[i].getId());

            }

        }

        System.out.println("total records: " + elements);

    }


    /**
     * gets size of hashTable
     * 
     * @return size
     */
    public int getSize() {

        return size;
    }


    /**
     * gets hashTable array
     * 
     * @return hash
     */
    public Handle[] getTable() {

        return hash;
    }


    /**
     * gets number of elements in hashTable
     * 
     * @return elements
     */
    public int getElements() {

        return elements;
    }


    private int p(int K, int i) {

        return i * ((((K / size) % (size / 2)) * 2) + 1);
    }


    /**
     * 
     * @return
     *         true if its half full
     */
    public boolean isFull() {
        return (elements > size / 2);

    }

}
