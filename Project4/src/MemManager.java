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
        int freeSize = log(poolsize);
        freeBlock = new FreeBlock[freeSize];
        int begIndex = 0;
        freeBlock[0] = new FreeBlock(begIndex);
        for (int i = 1; i < freeSize; i++) {
            begIndex += (int)Math.pow(2, i);
            freeBlock[i] = new FreeBlock(begIndex);
        }
        freeBlock[freeSize - 1].setNext(new FreeBlock(0));

    }

    // Insert a record and return its position handle.

    // space contains the record to be inserted, of length size.


    Handle insert(byte[] space, int size) throws Exception {

        // returns where in the array we have space for this data
        int block = space(size);
        if (block == -1) {
            return null;
        }

        int givenBlockSize = (int)Math.pow(2, block);

        while (size <= givenBlockSize / 2) {
            splitBlock(block);
            return (insert(space, size));
        }

        int startIndex = freeBlock[block].getBegIndex();
        System.arraycopy(space, 0, freeBlock, startIndex, size);
        freeBlock[block].movePointer();

        return (new Handle(Seminar.deserialize(space).getID(), startIndex,
            size));

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
     * splits the block into 2
     * 
     * @param block
     *            takes in the block we are trying to split
     */
    public void splitBlock(int block) {
        int currSize = freeBlock[block].getNext().getBegIndex();
        freeBlock[block].setNext(null);
        freeBlock[block - 1].setNext(new FreeBlock(currSize / 2));
        freeBlock[block - 1].getNext().setNext(new FreeBlock(currSize / 2));

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
        int start = log(size);

        // loops from there forward seeing if there is space
        for (int i = start; i < this.freeBlock.length; i++) {
            if (freeBlock[i].hasNext()) {
                return (i);
            }
        }
        // runs resize if no fit is found
        return (-1);
    }


    /**
     * 
     * @param size
     *            takes in the size of a block
     * @return
     *         returns the log base 2 of it
     */
    private int log(int size) {
        int i = 0;
        while (Math.pow(2, i) <= size) {
            i++;
        }
        return i;
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
