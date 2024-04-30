import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Comment...
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
        data = new Database();
    }
    
    /**
     * This method parses keywords in the line and calls methods in the
     * database as needed. Each line command will be specified by one of the
     * keywords to perform the actions. These actions are performed on objects
     * Processor method is called for each line. This method only calls the 
     * corresponding methods for each command. The methods called 
     * will write to the console.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(int memSize, int hashSize, String file) {
        
        File f = null;
        // Attempts to open the file and scan through it
        try {

            // takes the first command line argument and opens that file
            f = new File(file);

            // creates a scanner object
            Scanner scanner = new Scanner(f);
            

            // creates a command processor object
            CommandProcessor cmdProc = new CommandProcessor();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!line.trim().isEmpty()) {
                    
                    String[] arr = line.trim().split("\\s+");
                    String command = arr[0];
                    
                    if (command.equals("insert")) {
                        
                        
                        String title = scanner.nextLine().trim();
                        
                        String[] dateln = scanner.nextLine().trim().split("\\s+");
                        
                        String date = dateln[0];
                        int length = Integer.parseInt(dateln[1]);
                        int x = Integer.parseInt(dateln[2]);
                        int y = Integer.parseInt(dateln[3]);
                        int cost = Integer.parseInt(dateln[4]);
                        
                        String[] keywords = scanner.nextLine().trim().split("\\s+");
                        
                        String desc = scanner.nextLine().trim();
                        
                    }
                    else if (command.equals("delete")) {
                        
                    }
                    else if (command.equals("search")) {
                        
                    }
                    else if (command.equals("print")) {
                        
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