/**
 * Comment...
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class MemManager {

    byte[] mem;
    FreeBlock[] freeBlock;

    /**
     * constructor for the memory pool
     * 
     * @param poolsize
     *            the size given for memory pool
     *            makes an array keeping track of free blocks
     */
    public MemManager(int poolsize) {

        mem = new byte[poolsize];
        int freeSize = (int)Math.log(poolsize);
        freeBlock = new FreeBlock[freeSize];
        for (int i = 0; i < freeSize; i++) {
            freeBlock[i] = new FreeBlock((int)Math.pow(2, i + 1));
        }
        freeBlock[freeSize - 1].setNext(new FreeBlock(0));
    }

    // Insert a record and return its position handle.

    // space contains the record to be inserted, of length size.


    Handle insert(byte[] space, int size) {

        // returns where in the array we have space for this data
        int block = space(size);
        if (block == -1) {
            return null;
        }

        return null;

    }


    /**
     * 
     * @return
     *         returns current length of mem manager
     */
    public int getLength() {
        return (this.mem.length);
    }


    /**
     * 
     * @param size
     *            size of record to be added
     * @return
     *         returns int based on if it'd fit
     */
    private int space(int size) {
        // gets the smallest block that could store it
        int start = (int)Math.log(size);

        // loops from there forward seeing if there is space
        for (int i = start; i < this.freeBlock.length; i++) {
            if (freeBlock[i].hasNext()) {
                return (i);
            }
        }
        // runs resize if no fit is found
        this.resize();
        return (this.space(size));
    }


    /**
     * resizes the memory block if it is not large enough
     */
    private MemManager resize() {
        MemManager revised = new MemManager(mem.length * 2);

    }


    // Return the length of the record associated with theHandle
    /**
     * 
     * @param theHandle
     *            takes in a handle
     * @return
     *         returns the length
     */
    int length(Handle theHandle) {
        return theHandle.getLength();

    }

    // Free a block at the position specified by theHandle.

    // Merge adjacent free blocks.


    void remove(Handle theHandle) {

    }

    // Return the record with handle posHandle, up to size bytes, by // copying
    // it into space.

    // Return the number of bytes actually copied into space.


    int get(byte[] space, Handle theHandle, int size) {

        return 0;
    }

    // Dump a printout of the freeblock list


    void dump() {

    }
}
