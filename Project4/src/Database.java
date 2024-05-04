/**
 * Comment...
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class Database {

    private MemManager mem;
    private HashTable hash;

    public Database(int memSize, int hashSize) {

        mem = new MemManager(memSize);
        hash = new HashTable(hashSize);

    }


    public void insert(
        int id,
        String title,
        String date,
        int length,
        short x,
        short y,
        int cost,
        String[] keywords,
        String desc)
        throws Exception {

        // creates the seminar object serializing it and insering it to
        // memManager
        Seminar s = new Seminar(id, title, date, length, x, y, cost, keywords,
            desc);
        byte[] serialized = s.serialize();
        Handle result = mem.insert(serialized, serialized.length);

        // if the manager has no room to insert the seminar it will return null
        // we then know to resize it, repopulate it and then add the seminar
        if (result == null) {
            this.resizeMem();
            result = mem.insert(serialized, serialized.length);
        }

        hash.hashInsert(result);

    }


    /**
     * resizes the memManager when it cant hold a record
     * 
     * @throws Exception
     */
    private void resizeMem() throws Exception {
        MemManager memNew = new MemManager(mem.getMemLength() * 2);
        HashTable hashNew = new HashTable(hash.getSize());
        // loop through hashtable and remove from old add to new

        Handle temp;
        byte[] tempArr;
        Handle[] table = hash.getTable();

        // loops through the table re-adding all the records
        for (int i = 0; i < hash.getSize(); i++) {
            temp = table[i];
            if (temp.getId() != -1) {
                tempArr = new byte[temp.getLength()];
                mem.get(tempArr, temp);
                temp = memNew.insert(tempArr, tempArr.length);
                hashNew.hashInsert(temp);
            }
        }

        mem = memNew;
        hash = hashNew;
    }


    public void delete(int id) {

    }


    public void search(int id) {

    }


    /**
     * 
     */
    public void printH() {

    }


    /**
     * 
     */
    public void printF() {
        mem.dump();
    }

}
