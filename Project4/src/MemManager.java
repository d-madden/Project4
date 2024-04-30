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
    MemManager(int poolsize) {
        mem = new byte[poolsize];
        int freeSize = (int)Math.log(poolsize);
        freeBlock = new FreeBlock[freeSize];
        freeBlock[freeSize - 1].setNext(new FreeBlock(0));
    }

    // Insert a record and return its position handle.

    // space contains the record to be inserted, of length size.


    Handle insert(byte[] space, int size) {

        return null;

    }

    // Return the length of the record associated with theHandle


    int length(Handle theHandle) {

        return 0;

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
