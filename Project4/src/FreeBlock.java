/**
 * Comment...
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class FreeBlock {

    private int begIndex;
    private FreeBlock next;

    /**
     * constructor for free blocks
     * 
     * @param begIn
     *            beginning index of the free block
     */
    public FreeBlock(int begIn) {
        begIndex = begIn;
        next = null;
    }


    /**
     * 
     * @return
     *         returns the beginning index of the freeBlock
     */
    public int getBegIndex() {
        return begIndex;
    }


    /**
     * 
     * @param free
     *            the free block we want to be set next
     * 
     */
    public void setNext(FreeBlock free) {
        next = free;
    }


    /**
     * 
     * @return
     *         returns boolean based on if the freeblock is linked to another
     */
    public boolean hasNext() {
        return (this.next != null);
    }


    /**
     * 
     * @return
     *         returns the next freeblock
     */
    public FreeBlock getNext() {
        return next;
    }

}
