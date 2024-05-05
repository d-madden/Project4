/**
 * Database delegates commands and prints output
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class Database {

    private MemManager mem;
    private HashTable hash;

    /**
     * constructor for database
     * 
     * @param memSize
     *            is the size of the memory pool
     * @param hashSize
     *            is the hashsize
     */
    public Database(int memSize, int hashSize) {

        mem = new MemManager(memSize);
        hash = new HashTable(hashSize);

    }


    /**
     * insert function inserts new
     * 
     * @param id
     *            identification number
     * @param title
     *            title
     * @param date
     *            date of record
     * @param length
     *            length of the record
     * @param x
     *            x of the record
     * @param y
     *            y of the record
     * @param cost
     *            cost of the record
     * @param keywords
     *            keywords of the record
     * @param desc
     *            description of the record
     * @throws Exception
     */
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
        System.out.println(s);
        System.out.println("Size: " + result.getLength());

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
            if (temp != null && temp.getId() != -1) {
                tempArr = new byte[temp.getLength()];
                mem.get(tempArr, temp);
                temp = memNew.insert(tempArr, tempArr.length, temp.getId());
                hashNew.hashInsert(temp);
            }
        }

        mem = memNew;
        hash = hashNew;

        System.out.println("Memory pool expanded to " + mem.getMemLength()
            + " bytes");
    }


    /**
     * delete deletes something
     * 
     * @param id
     *            id of what we are deleting
     */
    public void delete(int id) {
        Handle temp = hash.hashDelete(id);

        // if the id is not in the hash
        if (temp == null || temp.getId() == -1) {
            System.out.println("Delete FAILED -- There is no record with ID "
                + id);
        }
        else {
            mem.remove(temp);
            System.out.println("Record with ID " + id
                + " successfully deleted from the database");
        }

    }


    /**
     * search method
     * 
     * @param id
     *            takes id you searching for
     * @throws Exception
     */
    public void search(int id) throws Exception {
        Handle temp = hash.hashSearch(id);

        if (temp != null) {
            System.out.println("Found record with ID " + id + ":");
            byte[] tempArr = new byte[temp.getLength()];
            mem.get(tempArr, temp);
            Seminar sem = Seminar.deserialize(tempArr);
            System.out.println(sem.toString());

        }
        else {
            System.out.println("Search FAILED -- There is no record with ID "
                + id);
        }

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
