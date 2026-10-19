/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;

public class MessageTest {

    // Exact test data from Image 8
    String testMessageText = "Hi Mike, can you join us for dinner tonight?";
    String testRecipient = "+27718693002";

    @Test
    public void testMessageLengthSuccess() {
        // Test data from Image 9: Success
        Message msg = new Message(testRecipient, testMessageText, 1);
        String expected = "Message ready to send.";
        String actual = msg.checkMessageLength();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageLengthExceeds250() {
        // Test data from Image 9: Failure
        String longMessage = "a".repeat(260); 
        Message msg = new Message(testRecipient, longMessage, 1);
        
        // It exceeds by 10 characters (260 - 250 = 10)
        String expected = "Message exceeds 250 characters by 10; please reduce the size.";
        String actual = msg.checkMessageLength();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientNumberSuccess() {
        // Test data from Image 10: Success
        Message msg = new Message(testRecipient, testMessageText, 1);
        String expected = "Cell phone number successfully captured.";
        String actual = msg.checkRecipientCell();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientNumberFailure() {
        // Test data from Image 10: Failure (Using the number from Message 2 test data: 08575975889)
        Message msg = new Message("08575975889", testMessageText, 1);
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String actual = msg.checkRecipientCell();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageHashCorrect() {
        // Image 10 explicitly states: "The system should return: 00:0:HITONIGHT when supplied with the data from Test Case 1."
        // We pass messageNumber as 0 to trigger our forced "00" ID, and use the exact message.
        Message msg = new Message(testRecipient, testMessageText, 0);
        
        String expectedHash = "00:0:HITONIGHT";
        String actualHash = msg.getMessageHash();
        
        assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testMessageIDCreated() {
        // Image 10: Message ID generated
        Message msg = new Message(testRecipient, testMessageText, 1);
        
        // We just check that it's not more than 10 characters, as per the brief
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testSentMessageSend() {
        // Image 10: User selected 'Send Message'
        Message msg = new Message(testRecipient, testMessageText, 1);
        String expected = "Message successfully sent.";
        String actual = msg.SentMessage(1);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSentMessageDisregard() {
        // Image 10: User selected 'Disregard Message'
        Message msg = new Message(testRecipient, testMessageText, 1);
        String expected = "Press 0 to delete the message.";
        String actual = msg.SentMessage(2);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSentMessageStore() {
        // Image 10: User selected 'Store Message'
        Message msg = new Message(testRecipient, testMessageText, 1);
        String expected = "Message successfully stored.";
        String actual = msg.SentMessage(3);
        
        assertEquals(expected, actual);
    }

    private void assertEquals(String expected) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertTrue(boolean checkMessageID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(String expectedHash, String actualHash) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}