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
    private static Handle emptyHandle;

    /**
     * hashtable constructor
     * @param hashSize
     */
    public HashTable(int hashSize) {

        size = hashSize;
        hash = new Handle[size];
        emptyHandle = new Handle(-1, 0, 0);

        // populates the array with tombstones
        for (int i = 0; i < size; i++) {
            hash[i] = emptyHandle;
        }
    }


    /**
     * inserts into hashtable
     * @param e
     */
    public void hashInsert(Handle e) {

        int id = e.getId();

        int home; // Home position for e
        int pos;
        
        pos = id % size; // Initial position is the home slot
        home = id % size;

        for (int i = 1; hash[pos] != emptyHandle; i++) {
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
     * searches hashtable
     * @param id
     *          identification
     * @return boolean
     */
    public Handle hashSearch(int id) {

        int home; // Home position for K
        Handle e = null;
        int pos;
        pos = id % size; // Initial position is the home slot
        home = id % size;
        for (int i = 1; (id != (hash[pos]).getId())
            && (hash[pos] != emptyHandle); i++) {

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
     * deletes hashtable
     * @param id
     *          id imagine this is the id of element to delete
     * @return removed handle
     */
    public Handle hashDelete(int id) {

        int home; // Home position for K
        int pos;
        pos = id % size; // Initial position is the home slot
        home = id % size;
        Handle removed = emptyHandle;

        for (int i = 1; (id != (hash[pos]).getId())
            && (hash[pos] != emptyHandle); i++) {

            pos = (home + p(id, i)) % size; // Next on probe
                                            // sequence
        }
        if (id == (hash[pos]).getId()) { // Found it

            removed = hash[pos];
            hash[pos] = emptyHandle;
            elements--;

            return removed;
        }
        else {
            return emptyHandle; // K not in hash table
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

    /**
     * 
     * @return
     *         true if its half full
     */
    public boolean isFull() {
        return (elements > size / 2);

    }

    private int p(int k, int i) {

        return i * ((((k / size) % (size / 2)) * 2) + 1);
    }

}
