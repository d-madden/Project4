/**
 * The class containing the tombstone
 *
 * @author Daniel Madden
 * @author Jordan DeNaro
 * @version 04-21-2024
 */
public class TombstoneTest extends student.TestCase {
    
    /**
     * tests the tombstone utilization
     */
    public void testSingletonBehavior() {
        // Reset the singleton instance for a clean start
        assertEquals(Tombstone.getInstance().getId(), -1);
        
        // Test the creation of the instance.
        Tombstone firstInstance = Tombstone.getInstance();
        assertNotNull("The first instance should not be null", firstInstance);

        // Test that subsequent calls return the same instance.
        Tombstone secondInstance = Tombstone.getInstance();
        assertSame(
            "The second instance should be the same as the first instance",
            firstInstance, secondInstance);
        
    }
    
}
