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
    private static Handle emptyHandle = null;

    /**
     * hashtable constructor
     * 
     * @param hashSize
     *            size of the hash table
     */
    public HashTable(int hashSize) {

        size = hashSize;
        elements = 0;
        hash = new Handle[size];

    }


    /**
     * inserts into hashtable
     * 
     * @param e
     *            the handle we are inserting
     */
    public void hashInsert(Handle e) {

        int id = e.getId();

        int home; // Home position for e
        int pos;

        pos = id % size; // Initial position is the home slot
        home = id % size;

        // resizes hash if half full
        if ((elements + 1) > (size / 2)) {
            resize();
            System.out.println("Hash table expanded to " + getSize()
                + " records");
        }

        for (int i = 1; hash[pos] != emptyHandle && hash[pos]
            .getId() != -1; i++) {
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
     * 
     * @param id
     *            identification
     * @return boolean
     */
    public Handle hashSearch(int id) {

        Handle e = null;

        int home; // Home position for K
        int pos;

        pos = id % size; // Initial position is the home slot
        home = id % size;
        for (int i = 1; (hash[pos] != emptyHandle) && (id != (hash[pos])
            .getId()); i++) {

            pos = (home + p(id, i)) % size; // Next on probe
                                            // sequence
        }
        if ((hash[pos] != emptyHandle) && (id == (hash[pos]).getId())) {

            e = hash[pos];

            return e;
        }
        else {
            return e; // K not in hash table
        }
    }


    /**
     * deletes hashtable
     * 
     * @param id
     *            id imagine this is the id of element to delete
     * @return removed handle
     */
    public Handle hashDelete(int id) {

        int home; // Home position for K
        int pos;

        pos = id % size; // Initial position is the home slot
        home = id % size;

        Handle removed = emptyHandle;

        for (int i = 1; (hash[pos] != emptyHandle) && (id != (hash[pos])
            .getId()); i++) {

            pos = (home + p(id, i)) % size; // Next on probe
                                            // sequence
        }
        if ((hash[pos] != emptyHandle) && (id == (hash[pos]).getId())) {

            removed = hash[pos];
            hash[pos] = Tombstone.getInstance();
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

            if (hash[i] != null && hash[i].getId() != -1) {

                System.out.println(i + ": " + hash[i].getId());

            }
            else if (hash[i] != null) {

                System.out.println(i + ": TOMBSTONE");

            }

        }

        System.out.println("total records: " + elements);

    }


    /**
     * resizes hash table
     * 
     */
    public void resize() {

        int oldSize = this.size;
        Handle[] oldArr = this.hash;

        this.size = size * 2;
        this.hash = new Handle[size];
        this.elements = 0;

        for (int i = 0; i < oldSize; i++) {

            if (oldArr[i] == null) {

                continue;
            }
            else if (oldArr[i].getId() == -1) {

                continue;
            }
            else {
                this.hashInsert(oldArr[i]);
            }

        }

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
     * mod formula for traversal
     * 
     * @return answer
     * answer to mod formula for given point
     */
    public int p(int k, int i) {

        return i * ((((k / size) % (size / 2)) * 2) + 1);
    }

}
