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
    
    public Database(int memSize, int hashSize, String file) {
        
        mem = new MemManager(memSize);
        hash = new HashTable(hashSize);
        
    }
    
    public void insert(int id, 
        String title, 
        String date, 
        int length, 
        short x, 
        short y, 
        int cost, 
        String[] keywords, 
        String desc) throws Exception {
        
        //creates the seminar object serializing it and insering it to memManager
        Seminar s = new Seminar(id, title, date, length, x, y, cost, keywords, desc);
        byte [] serialized = s.serialize();
        Handle result = mem.insert(serialized, serialized.length);
        
        //if the manager has no room to insert the seminar it will return null
        //we then know to resize it, repopulate it and then add the seminar
        if(result == null) {
            this.resizeMem();
            result = mem.insert(serialized, serialized.length);
        }
        
        
        
    }
    
    private void resizeMem() {
        MemManager memNew = new MemManager(mem.getMemLength()*2);
        HashTable hashNew = new HashTable(hash.size);
        //loop through hashtable and remove from old add to new
        
        mem = memNew;
        hash = hashNew;
    }

    public void delete(int id) {
        
    }
    
    public void search(int id) {
        
    }
    
    public void print() {
        
    }
    
}