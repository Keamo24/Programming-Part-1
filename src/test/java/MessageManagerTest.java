/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageManagerTest {

    @BeforeEach
    public void setUp() {
        MessageManager.populateTestData(); 
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        // Test Data: Developer entry for 1-4
        // Expected: "Did you get the cake?", "It is dinner time!"
        assertEquals("Did you get the cake?", MessageManager.sentMessages.get(0).getMessageText());
        assertEquals("It is dinner time!", MessageManager.sentMessages.get(1).getMessageText());
    }

    @Test
    public void testDisplayLongestMessage() {
        // Test Data: message 1-4
        // Expected: "Where are you? You are late! I have asked you to be on time."
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, MessageManager.getLongestMessage());
    }

    @Test
    public void testSearchForMessageID() {
        // Test Data: message 4
        // Expected: "It is dinner time!"
        // Since the ID is random, we get the ID dynamically from the test data
      // Gets the ID of Message 2 (which IS stored)
String msgID = MessageManager.storedMessages.get(0).getMessageID(); 
assertEquals("Where are you? You are late! I have asked you to be on time.", MessageManager.searchByID(msgID));
    }

    @Test
    public void testSearchByRecipient() {
        // Test Data: +27838884567
        // Expected: "Where are you? You are late! I have asked you to be on time." AND "Ok, I am leaving without you."
        String expected = "Where are you? You are late! I have asked you to be on time.\nOk, I am leaving without you.";
        assertEquals(expected, MessageManager.searchByRecipient("+27838884567"));
    }

    @Test
    public void testDeleteMessageByHash() {
        // Test Data: Test Message 2
        // Expected: "Message: "Where are you? You are late! I have asked you to be on time." successfully deleted."
        String hashToDelete = MessageManager.storedMessages.get(0).getMessageHash(); // Message 2 is index 0 in stored
        String expected = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        
        assertEquals(expected, MessageManager.deleteByHash(hashToDelete));
        assertEquals(1, MessageManager.storedMessages.size()); // Check it was actually removed
    }

    @Test
    public void testDisplayReport() {
        // Test Data: The system returns a report showing Hash, Recipient, and Message
        String report = MessageManager.displayReport();
        
        assertTrue(report.contains("Hash:"));
        assertTrue(report.contains("Recipient:"));
        assertTrue(report.contains("Message:"));
        assertTrue(report.contains("Where are you? You are late! I have asked you to be on time."));
    }
}