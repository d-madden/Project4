import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * command processor for the project reads commands and calls methods
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    private Database data;

    /**
     * The constructor for the command processor requires an instance of
     * database to exist, so the only constructor takes a database class
     * object to feed commands to.
     */
    public CommandProcessor() {

    }


    /**
     * This method parses keywords in the line and calls methods in the
     * database as needed. Each line command will be specified by one of the
     * keywords to perform the actions. These actions are performed on objects
     * Processor method is called for each line. This method only calls the
     * corresponding methods for each command. The methods called
     * will write to the console.
     * 
     * @param memSize
     *            size of memory
     * @param hashSize
     *            size of hash table
     * @param fileName
     *            name of input file
     * @throws Exception
     */
    public void processor(int memSize, int hashSize, String fileName)
        throws Exception {

        File f = null;
        // Attempts to open the file and scan through it
        data = new Database(memSize, hashSize);

        try {

            // takes the first command line argument and opens that file
            f = new File(fileName);

            // creates a scanner object
            Scanner scanner = new Scanner(f);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!line.trim().isEmpty()) {

                    String[] arr = line.trim().split("\\s+");

                    String command = arr[0];

                    if (command.equals("insert")) {

                        int id = Integer.parseInt(arr[1]);

                        String title = scanner.nextLine().trim();

                        String[] dateln = scanner.nextLine().trim().split(
                            "\\s+");

                        String date = dateln[0];
                        int length = Integer.parseInt(dateln[1]);
                        short x = Short.parseShort(dateln[2]);
                        short y = Short.parseShort(dateln[3]);
                        int cost = Integer.parseInt(dateln[4]);

                        String[] keywords = scanner.nextLine().trim().split(
                            "\\s+");

                        String desc = scanner.nextLine().trim();

                        data.insert(id, title, date, length, x, y, cost,
                            keywords, desc);

                    }
                    else if (command.equals("delete")) {

                        int id = Integer.parseInt(arr[1]);

                        data.delete(id);
                    }
                    else if (command.equals("search")) {

                        int id = Integer.parseInt(arr[1]);

                        data.search(id);
                    }
                    else if (command.equals("print")) {

                        if (arr[1].equals("hashtable")) {
                            data.printH();
                        }
                        else {
                            data.printF();
                        }

                    }
                    else {

                        // if the command is not ine of the above
                        // a message is written to console
                        System.out.println("Unrecognized command.");

                    }
                }

            }

            // closes the scanner
            scanner.close();
        }
        // catches the exception if the file cannot be found
        // and outputs the correct information to the console
        catch (FileNotFoundException e) {
            System.out.println("Invalid file");
            e.printStackTrace();
        }

    }

}
