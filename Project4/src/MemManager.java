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
        freeBlock = new FreeBlock[freeSize - 1];
        for (int i = 0; i < freeSize - 1; i++) {
            freeBlock[i] = new FreeBlock((int)Math.pow(2, i + 1));
        }
        freeBlock[freeSize - 2].setNext(new FreeBlock(0));

    }

    // Insert a record and return its position handle.

    // space contains the record to be inserted, of length size.


    /**
     * 
     * @param space
     *            byte array of the serialized seminar
     * @param size
     *            size of the serialized seminar
     * @return
     *         returns a handle receipt of the stored memory
     * @throws Exception
     */
    Handle insert(byte[] space, int size) throws Exception {

        // returns where in the array we have space for this data
        int block = space(size);
        if (block == -1) {
            return null;
        }

        int givenBlockSize = (int)Math.pow(2, block + 1);

        while (size <= givenBlockSize / 2) {
            splitBlock(block);
            return (insert(space, size));
        }

        int startIndex = freeBlock[block].getNext().getBegIndex();
        System.arraycopy(space, 0, mem, startIndex, size);
        freeBlock[block].movePointer();

        return (new Handle(Seminar.deserialize(space).getID(), startIndex,
            size));

    }


    /**
     * 
     * @return
     *         returns current length of mem manager
     */
    public int getMemLength() {
        return (this.mem.length);
    }


    /**
     * splits the block into 2
     * 
     * @param block
     *            takes in the block we are trying to split
     */
    private void splitBlock(int block) {
        int begIndex = freeBlock[block].getNext().getBegIndex();
        int currSize = freeBlock[block].getBegIndex();
        freeBlock[block].setNext(freeBlock[block].getNext().getNext());
        freeBlock[block - 1].setNext(new FreeBlock(begIndex));
        freeBlock[block - 1].getNext().setNext(new FreeBlock(begIndex + currSize
            / 2));

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
        int start = log(size) - 1;

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
        int handSize = theHandle.getLength();
        int handStart = theHandle.getStart();

        // frees up all the memory previosuly used
        for (int i = handStart; i < handSize; i++) {
            mem[i] = 0;
        }

        int block = log(handSize) - 1;

        if (freeBlock[block].getNext() == null) {
            freeBlock[block].setNext(new FreeBlock(handStart));
        }
        else {
            compress(block, handStart);
        }

    }


    /**
     * 
     * @param block
     *            takes in the block we are compressing from
     * @param handStart
     *            the start position of the block
     */
    private void compress(int block, int handStart) {

        if (freeBlock[block + 1].getNext() == null) {
            freeBlock[block + 1].setNext(new FreeBlock(handStart));
            freeBlock[block].setNext(null);
        }
        else {
            int min = Math.min(handStart, freeBlock[block].getNext()
                .getBegIndex());
            freeBlock[block].setNext(null);
            compress(block + 1, min);
        }

    }

    // Return the record with handle posHandle, up to size bytes, by // copying
    // it into space.

    // Return the number of bytes actually copied into space.


    /**
     * 
     * @param space
     *            the space we are copying the data into
     * @param theHandle
     *            the handle directing where the data is coming from
     * 
     * @return
     *         returns the amount of bytes pulled
     */
    int get(byte[] space, Handle theHandle) {

        int handSize = theHandle.getLength();
        int handStart = theHandle.getStart();

        System.arraycopy(mem, handStart, space, 0, handSize);

        return handSize;
    }

    // Dump a printout of the freeblock list


    /**
     * prints out all the freeBlocks and their start indicies
     */
    void dump() {

        System.out.println("Freeblock List:");
        for (int i = 0; i < freeBlock.length; i++) {
            if (freeBlock[i].getNext() != null) {
                System.out.print(freeBlock[i].getBegIndex() + ": ");
                FreeBlock curr = freeBlock[i].getNext();
                while (curr != null) {
                    System.out.print(curr.getBegIndex());
                    curr = curr.getNext();
                }
                System.out.println();
            }
        }
    }
}
