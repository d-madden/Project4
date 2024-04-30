/**
 * Comment...
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class Handle {
    private int ID;
    private int start;
    private int length;

    /**
     * 
     * @param Id
     *            the ID of the record stored
     * @param st
     *            the start of the record stored in the Mem Manager
     * @param ln
     *            the length of the record
     */
    public Handle(int Id, int st, int ln) {
        ID = Id;
        start = st;
        length = ln;

    }


    /**
     * 
     * @return
     *         returns the ID
     */
    public int getId() {
        return ID;
    }


    /**
     * 
     * @return
     *         returns the start of the record
     */
    public int getStart() {
        return start;
    }


    /**
     * 
     * @return
     *         returns the length of the record
     */
    public int getLength() {
        return length;
    }

}
