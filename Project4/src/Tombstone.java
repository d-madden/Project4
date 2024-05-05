/**
 * The class containing the tombstone 
 *
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class Tombstone extends Handle {

    private static Tombstone tomb = null;

    /**
     * constructor for tombstone
     */
    public Tombstone() {
        
        super(-1, 0, 0);
    }


    /**
     * 
     * tombstone get instance
     * 
     * @return
     *         returns the tomb
     */
    public static Tombstone getInstance() {

        if (tomb == null) {
            
            tomb = new Tombstone();
        }

        return tomb;
    }

}