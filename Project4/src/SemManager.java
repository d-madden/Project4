/**
 * The class containing the main method.
 *
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */

// On my honor:
// - I have not used source code obtained from another current or
//   former student, or any other unauthorized source, either
//   modified or unmodified.
//
// - All source code and documentation used in my program is
//   either my original work, or was derived by me from the
//   source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
//   anyone other than my partner (in the case of a joint
//   submission), instructor, ACM/UPE tutors or the TAs assigned
//   to this course. I understand that I may discuss the concepts
//   of this program with other students, and that another student
//   may help me debug my program so long as neither of us writes
//   anything during the discussion or modifies any computer file
//   during the discussion. I have violated neither the spirit nor
//   letter of this restriction.
//   (Daniel Madden & Jordan DeNaro)


public class SemManager {
    /**
     * @param args
     *     Command line parameters
     */
    public static void main(String[] args) {
        // This is the main file for the program.
        
        //the inital size of the memory manager
        int memSize = Integer.parseInt(args[0]);
        
        //is the initial size of the hash table
        int hashSize = Integer.parseInt(args[1]);
        
        CommandProcessor cmd = new CommandProcessor();
        cmd.processor(memSize, hashSize, args[2]);


        
        
    }

}
