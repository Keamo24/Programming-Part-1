/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;

public class MessageTest {

    // We will use this message text for the hash and length tests
    String testMessageText = "Hi Mike, can you join us for dinner tonight?";
    // The expected hash from Image 9 based on the test data
 

    @Test
    public void testMessageLengthSuccess() {
        // Test data: "Hi Mike, can you join us for dinner tonight?" (46 characters)
        Message msg = new Message("+27718693002", testMessageText, 0);
        String expected = "Message ready to send.";
        String actual = msg.checkMessageLength();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageLengthExceeds250() {
        // Creating a string longer than 250 characters
        String longMessage = "a".repeat(260); 
        Message msg = new Message("+27718693002", longMessage, 0);
        
        // It exceeds by 10 characters (260 - 250 = 10)
        String expected = "Message exceeds 250 characters by 10; please reduce the size.";
        String actual = msg.checkMessageLength();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientNumberSuccess() {
        // Test data: +27718693002
        Message msg = new Message("+27718693002", testMessageText, 0);
        String expected = "Cell phone number successfully captured.";
        String actual = msg.checkRecipientCell();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientNumberFailure() {
        // Test data without international code: 08575975889
        Message msg = new Message("08575975889", testMessageText, 0);
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String actual = msg.checkRecipientCell();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageHashCorrect() {
        // We pass messageNumber as 0 so it matches the "00:0:HITONIGHT" format
        Message msg = new Message("+27718693002", testMessageText, 0);
        
        // The generated hash should end with HITONIGHT and match the format
        String actualHash = msg.getMessageHash();
        
        // Asserting that the hash ends with the first and last words (HITONIGHT)
        assertTrue("Hash should contain HITONIGHT", actualHash.endsWith("HITONIGHT"));
        
        // Asserting the full format matches (assuming the random ID starts with 00 for the test)
        // Note: Because the ID is randomly generated, we can't guarantee it starts with "00" 
        // unless we manually set it. So we check the message number and words part.
        assertTrue("Hash format incorrect", actualHash.contains(":0:HITONIGHT"));
    }

    @Test
    public void testMessageIDCreated() {
        Message msg = new Message("+27718693002", testMessageText, 0);
        String _ = "Message ID generated: " + msg.getMessageID();
        // Note: This just verifies the ID is generated and formatted
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testSentMessageSuccess() {
        Message msg = new Message("+27718693002", testMessageText, 0);
        // Option 1 is "Send Message"
        String expected = "Message successfully sent.";
        String actual = msg.SentMessage(1);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message("+27718693002", testMessageText, 0);
        // Option 2 is "Disregard Message"
        String expected = "Press 0 to delete the message.";
        String actual = msg.SentMessage(2);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSentMessageStore() {
        Message msg = new Message("+27718693002", testMessageText, 0);
        // Option 3 is "Store Message"
        String expected = "Message successfully stored.";
        String actual = msg.SentMessage(3);
        
        assertEquals(expected, actual);
    }

    private void assertTrue(boolean checkMessageID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertTrue(String hash_format_incorrect, boolean contains) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(String expected, String actual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
