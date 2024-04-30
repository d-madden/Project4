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
        
        Seminar s = new Seminar(id, title, date, length, x, y, cost, keywords, desc);
        
        if(mem.insert(s.serialize(), 0) == null) {
            this.resizeMem();
        }
        
    }
    
    private void resizeMem() {
        MemManager memNew = new MemManager(mem.getLength()*2);
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