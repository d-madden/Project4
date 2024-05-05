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

        // resizes hash if half full
        if (hash.isFull()) {
            this.resizeHash();
        }

        if (hash.hashSearch(id) != null) {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + id);
            return;
        }

        // creates the seminar object serializing it and insering it to
        // memManager
        Seminar s = new Seminar(id, title, date, length, x, y, cost, keywords,
            desc);

        byte[] serialized = s.serialize();
        Handle result = mem.insert(serialized, serialized.length, id);

        // if the manager has no room to insert the seminar it will return null
        // we then know to resize it, repopulate it and then add the seminar
        while (result == null) {
            this.resizeMem();
            result = mem.insert(serialized, serialized.length, id);
        }

        hash.hashInsert(result);

        System.out.println("Successfully inserted record with ID " + id);
        System.out.println("ID: " + id + "," + "Title: " + title);
        System.out.println("Date: " + date);
        System.out.println("Description: " + desc);
        System.out.println("Keywords: " + keywords.toString());
        System.out.println("Size: " + result.getLength());

    }


    /**
     * resizes hash table
     */
    private void resizeHash() {
        HashTable hashnew = new HashTable(hash.getSize() * 2);

        Handle[] table = hash.getTable();
        Handle temp;
        for (int i = 0; i < hash.getSize(); i++) {
            temp = table[i];
            if (temp.getId() != -1) {
                hashnew.hashInsert(temp);
            }
        }

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
                temp = memNew.insert(tempArr, tempArr.length, temp.getId());
                hashNew.hashInsert(temp);
            }
        }

        mem = memNew;
        hash = hashNew;
    }


    public void delete(int id) {
        Handle temp = hash.hashDelete(id);

        // if the id is not in the hash
        if (temp == null) {
            System.out.print("Delete FAILED -- There is no record with ID "
                + id);
        }
        else {
            mem.remove(temp);
            System.out.print("Record with ID " + id
                + " successfully deleted from the database");
        }

    }


    public void search(int id) {

    }


    /**
     * dumps the hash
     */
    public void printH() {
        hash.dump();

    }


    /**
     * dumps the freetable
     */
    public void printF() {
        mem.dump();
    }

}
