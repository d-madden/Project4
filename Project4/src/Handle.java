/**
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class Handle {
    private int id;
    private int start;
    private int length;

    /**
     * 
     * @param iD
     *            the ID of the record stored
     * @param st
     *            the start of the record stored in the Mem Manager
     * @param ln
     *            the length of the record
     */
    public Handle(int iD, int st, int ln) {
        id = iD;
        start = st;
        length = ln;

    }


    /**
     * 
     * @return
     *         returns the ID
     */
    public int getId() {
        return id;
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
