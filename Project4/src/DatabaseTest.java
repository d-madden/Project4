/**
 * tests database
 * 
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class DatabaseTest extends student.TestCase {

    private Database data;

    /**
     * sets up the database test class
     */
    public void setUp() {
        data = new Database(256, 16);

    }


    /**
     * tests the resize for the memory manager
     * 
     * @throws Exception
     */
    public void testResize() throws Exception {
        String[] longKeywords = { "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, "
            + "Good, Good, Good, Good, Good, " + "Good, Good, Good" };

        String[] shortKeywords = { "hello, Time, ONE" };

        data.insert(10, "Seminar", "2405231000", 75, (short)15, (short)33, 125,
            shortKeywords, "This");

        data.insert(1729, "Seminar", "2405231000", 75, (short)15, (short)33,
            125, longKeywords, "This");

    }


    /**
     * tests the entire output
     * 
     * @throws Exception
     */
    public void testOutput() throws Exception {

        String[] arr = { "256", "256", "P1Sample_inputX.txt" };

        SemManager.main(arr);

        assertFuzzyEquals("Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of HCI research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Size: 173\n"
            + "Memory pool expanded to 512 bytes\n"
            + "Successfully inserted record with ID 2\n"
            + "ID: 2, Title: Computational Biology and Bioinformatics in CS at Virginia Tech\n"
            + "Date: 0610071600, Length: 60, X: 20, Y: 10, Cost: 30\n"
            + "Description: Introduction to   bioinformatics and computation biology\n"
            + "Keywords: Bioinformatics, computation_biology, Biology, Computer_Science, VT, Virginia_Tech\n"
            + "Size: 244\n"
            + "Memory pool expanded to 1024 bytes\n"
            + "Successfully inserted record with ID 3\n"
            + "ID: 3, Title: Computing Systems Research at VT\n"
            + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\n"
            + "Description: Seminar about the      Computing systems research at      VT\n"
            + "Keywords: high_performance_computing, grids, VT, computer, science\n"
            + "Size: 192\n"
            + "Insert FAILED - There is already a record with ID 3\n"
            + "Successfully inserted record with ID 10\n"
            + "ID: 10, Title: Overview of HPC and CSE Research at VT\n"
            + "Date: 0703301125, Length: 35, X: 0, Y: 0, Cost: 25\n"
            + "Description: Learn what kind of    research is done on HPC  and CSE at VT\n"
            + "Keywords: HPC, CSE, computer_science\n"
            + "Size: 168\n"
            + "Found record with ID 3:\n"
            + "ID: 3, Title: Computing Systems Research at VT\n"
            + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\n"
            + "Description: Seminar about the      Computing systems research at      VT\n"
            + "Keywords: high_performance_computing, grids, VT, computer, science\n"
            + "Hashtable: \n"
            + "1: 1\n"
            + "2: 2\n"
            + "3: 3\n"
            + "10: 10\n"
            + "total records: 4\n"
            + "Freeblock List:\n"
            + "There are no freeblocks in the memory pool\n"
            + "Record with ID 2 successfully deleted from the database\n"
            + "Search FAILED -- There is no record with ID 2\n"
            + "Hashtable: \n"
            + "1: 1\n"
            + "2: TOMBSTONE\n"
            + "3: 3\n"
            + "10: 10\n"
            + "total records: 3\n"
            + "Freeblock List:\n"
            + "256: 256\n"
            + "Delete FAILED -- There is no record with ID 6\n"
            + "Memory pool expanded to 2048 bytes\n"
            + "Successfully inserted record with ID 2\n"
            + "ID: 2, Title: Much More Computational Biology and Bioinformatics in CS at Virginia Tech\n"
            + "Date: 0610071600, Length: 60, X: 20, Y: 10, Cost: 30\n"
            + "Description: Introduction to bioinformatics and lots of computation biology\n"
            + "Keywords: Bioinformatics, computation_biology, Biology, Computer_Science, VT, Virginia_Tech\n"
            + "Size: 260\n"
            + "Record with ID 10 successfully deleted from the database\n"
            + "Hashtable: \n"
            + "1: 1\n"
            + "2: 2\n"
            + "3: 3\n"
            + "10: TOMBSTONE\n"
            + "total records: 3\n"
            + "Freeblock List:\n"
            + "1024: 512",
            systemOut().getHistory());
    }


    /**
     * tests the entire output
     * 
     * @throws Exception
     */
    public void testOutTwo() throws Exception {

        String[] arr = { "256", "256", "P1SimpSample_inputX.txt" };

        SemManager.main(arr);

        assertFuzzyEquals("Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview "
            + "of HCI research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Size: 173\n" + "Memory pool expanded to 512 bytes\n"
            + "Successfully inserted record with ID 2\n"
            + "ID: 2, Title: Computational Biology and Bioinformatics in CS "
            + "at Virginia Tech\n"
            + "Date: 0610071600, Length: 60, X: 20, Y: 10, Cost: 30\n"
            + "Description: Introduction to   bioinformatics and "
            + "computation " + "biology\n"
            + "Keywords: Bioinformatics, computation_biology, Biology, "
            + "Computer_Science, VT, Virginia_Tech\n" + "Size: 244\n"
            + "Memory pool expanded to 1024 bytes\n"
            + "Successfully inserted record with ID 3\n"
            + "ID: 3, Title: Computing Systems Research at VT\n"
            + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\n"
            + "Description: Seminar about the      Computing systems "
            + "research " + "at      VT\n"
            + "Keywords: high_performance_computing, grids, VT, computer, "
            + "science\n" + "Size: 192\n"
            + "Successfully inserted record with ID 10\n"
            + "ID: 10, Title: Overview of HPC and CSE Research at VT\n"
            + "Date: 0703301125, Length: 35, X: 0, Y: 0, Cost: 25\n"
            + "Description: Learn what kind of    research is done on HPC  "
            + "and CSE at VT\n" + "Keywords: HPC, CSE, computer_science\n"
            + "Size: 168\n", systemOut().getHistory());
    }

}
