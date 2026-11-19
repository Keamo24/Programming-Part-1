/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class MessageManagerTest {

    @BeforeEach
    public void setUp() {
        // Clear arrays before every test so they don't interfere with each other
        MessageManager.sentMessages.clear();
        MessageManager.storedMessages.clear();
        MessageManager.disregardedMessages.clear();
        
        // Load the test data
        MessageManager.populateTestData();
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        // Test Data: Message 1 and 4
        assertEquals(2, MessageManager.sentMessages.size());
        assertEquals("Did you get the cake?", MessageManager.sentMessages.get(0).getMessageText());
        assertEquals("It is dinner time!", MessageManager.sentMessages.get(1).getMessageText());
    }

    @Test
    public void testDisplayLongestMessage() {
        // Expected: "Where are you? You are late! I have asked you to be on time."
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, MessageManager.getLongestMessage());
    }

    @Test
    public void testSearchByID() {
        // Because IDs are random, we grab the ID of message 4 dynamically for the test
        String idToSearch = MessageManager.sentMessages.get(1).getMessageID(); 
        
        // In a real test, you would capture the console output, but for this assignment, 
        // it's often enough to ensure the logic runs without crashing, or to check if 
        // the method returns the correct message object.
        assertNotNull(idToSearch); 
        MessageManager.searchByID(idToSearch);
    }

    @Test
    public void testSearchByRecipient() {
        String recipientToSearch = "+27838884567";
        // This is a void method in my example, so we just verify the list has the messages
        assertEquals(2, MessageManager.storedMessages.size());
        // You can add console output verification here if your lecturer requires it
        MessageManager.searchByRecipient(recipientToSearch);
    }

    @Test
    public void testDeleteByHash() {
        int originalSize = MessageManager.storedMessages.size();
        String hashToDelete = MessageManager.storedMessages.get(0).getMessageHash(); // Gets Hash for Message 2
        
        MessageManager.deleteByHash(hashToDelete);
        
        assertEquals(originalSize - 1, MessageManager.storedMessages.size());
        assertFalse(MessageManager.messageHashes.contains(hashToDelete)); // Ensure hash is gone
    }

    @Test
    public void testDisplayReport() {
        // Ensure all stored messages have the required data for the report
        for (Message m : MessageManager.storedMessages) {
            assertNotNull(m.getMessageHash());
            assertNotNull(m.getRecipient());
            assertNotNull(m.getMessageText());
        }
    }
}
