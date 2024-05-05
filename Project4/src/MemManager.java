/**
 * memory manager class
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class MemManager {

    private byte[] mem;
    private FreeBlock[] freeBlock;

    /**
     * constructor for the memory pool
     * 
     * @param poolsize
     *            the size given for memory pool
     *            makes an array keeping track of free blocks
     */
    public MemManager(int poolsize) {

        mem = new byte[poolsize];
        int freeSize = log(poolsize) + 1;
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
     * @param id
     *            id of the seminar
     * @return
     *         returns a handle receipt of the stored memory
     * @throws Exception
     */
    Handle insert(byte[] space, int size, int id) throws Exception {

        // returns where in the array we have space for this data
        int block = space(size);
        while (block == -1) {
            this.resize();
            block = space(size);
        }

        int givenBlockSize = freeBlock[block].getBegIndex();

        while (size <= givenBlockSize / 2) {
            splitBlock(block);
            return (insert(space, size, id));
        }

        int startIndex = freeBlock[block].getNext().getBegIndex();
        System.arraycopy(space, 0, mem, startIndex, size);
        freeBlock[block].movePointer();

        return (new Handle(id, startIndex, size));

    }


    /**
     * resizes the memory manager
     */
    private void resize() {
        MemManager memNew = new MemManager(this.getMemLength() * 2);

        System.arraycopy(mem, 0, memNew.mem, 0, mem.length);

        for (int i = 0; i < freeBlock.length; i++) {
            memNew.freeBlock[i] = freeBlock[i];
        }
        memNew.freeBlock[memNew.freeBlock.length - 1].setNext(null);
        memNew.freeBlock[memNew.freeBlock.length - 2].setNext(new FreeBlock(
            memNew.freeBlock[memNew.freeBlock.length - 2].getBegIndex()));

        mem = memNew.mem;
        freeBlock = memNew.freeBlock;

        System.out.println("Memory pool expanded to " + mem.length + " bytes");

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
        while (Math.pow(2, i) < size) {
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


    /**
     * removes the handle from the memory manager
     * 
     * @param theHandle
     *            takes in a handle to remove
     */
    void remove(Handle theHandle) {
        int handSize = theHandle.getLength();
        int handStart = theHandle.getStart();

        // frees up all the memory previosuly used
        for (int i = handStart; i < handSize + handStart; i++) {
            mem[i] = 0;
        }

        int block = log(handSize) - 1;

        FreeBlock temp = this.buddy(block, handStart);
        if (temp != null) {
            compress(block, handStart, temp);
        }
        else {
            this.addToEnd(block, handStart);

        }

    }


    /**
     * adds the empty block to the correct position
     * 
     * @param block
     *            the block we are adding to
     * @param handStart
     *            the start of the empty block we adding
     * 
     */
    private void addToEnd(int block, int handStart) {
        if (freeBlock[block].getNext() == null) {
            freeBlock[block].setNext(new FreeBlock(handStart));
            return;
        }

        FreeBlock curr = freeBlock[block].getNext();
        FreeBlock prev = freeBlock[block];
        while (curr != null) {
            if (handStart < curr.getBegIndex()) {
                FreeBlock nBlock = new FreeBlock(handStart);
                nBlock.setNext(curr);
                prev.setNext(new FreeBlock(handStart));
                return;
            }
            else {
                curr = curr.getNext();
                prev = prev.getNext();
            }
        }
        prev.setNext(new FreeBlock(handStart));

    }


    /**
     * checks if the the start has a buddy and returns it
     * 
     * @param block
     *            the block we are looking for a buddy in
     * @param start
     *            the postion we are buddying up
     * @return
     *         returns the freeblock buddy if present
     */
    private FreeBlock buddy(int block, int start) {

        if (freeBlock[block].getNext() == null) {
            return null;
        }
        FreeBlock curr = freeBlock[block].getNext();

        while (curr != null) {
            int i = 1;
            while (Math.pow(2, block + 2) * i <= mem.length) {
                if (adjacent(curr.getBegIndex(), start, (int)Math.pow(2, block
                    + 1))) {
                    return curr;
                }
                i++;
            }
            curr = curr.getNext();
        }
        return null;
    }


    /**
     * checks if two blocks adjacent in terms of the freeblock
     * 
     * @param begIndex
     *            start index of the freeblock buddy
     * @param start
     *            start index of the new freeblock
     * @param size
     *            size of the blocks we are merging into
     * @return
     *         returns boolean
     */
    private boolean adjacent(int begIndex, int start, int size) {

        if (start < begIndex) {
            if (start + size == begIndex) {
                return true;
            }
        }
        else if (begIndex < start) {
            if (begIndex + size == start) {
                return true;
            }
        }
        return false;
    }


    /**
     * compresses the mem manager freeblock pool
     * 
     * @param block
     *            block we are compressing
     * @param handStart
     *            position we compress from
     * @param temp
     *            the buddy freeblock
     */
    private void compress(int block, int handStart, FreeBlock temp) {

        if (freeBlock[block + 1].getNext() == null) {
            freeBlock[block + 1].setNext(new FreeBlock(handStart));
            freeBlock[block].setNext(null);
        }
        else {
            int min = Math.min(handStart, temp.getBegIndex());
            FreeBlock curr = freeBlock[block].getNext();
            FreeBlock prev = freeBlock[block];
            while (curr != null) {
                if (curr.getBegIndex() == temp.getBegIndex() || curr
                    .getBegIndex() == handStart) {
                    prev.setNext(curr.getNext());
                    curr = null;

                }
                else {
                    curr = curr.getNext();
                    prev = prev.getNext();
                }
            }

            freeBlock[block + 1].setNext(new FreeBlock(handStart));
            FreeBlock temp2 = this.buddy(block, handStart);
            while (temp2 != null) {
                compress(block, handStart, temp2);
                temp2 = this.buddy(block + 1, handStart);
            }
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
        Boolean used = false;
        for (int i = 0; i < freeBlock.length; i++) {
            if (freeBlock[i].hasNext()) {
                used = true;
                System.out.print(freeBlock[i].getBegIndex() + ": ");
                FreeBlock curr = freeBlock[i];
                while (curr.hasNext()) {
                    curr = curr.getNext();
                    System.out.print(curr.getBegIndex() + " ");
                }
                System.out.println();
            }
        }
        if (!used) {
            System.out.println("There are no freeblocks in the memory pool");
        }
    }


    /**
     * 
     * @return
     *         returns the memory block array
     */
    public byte[] getMem() {

        return mem;
    }


    /**
     * 
     * @return
     *         returns the freeblock array
     */
    public FreeBlock[] getFree() {

        return freeBlock;
    }
}
