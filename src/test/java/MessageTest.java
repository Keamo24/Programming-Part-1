/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {

    // Exact test data from Image 8
    String testMessageText = "Hi Mike, can you join us for dinner tonight?";
    String testRecipient = "+27718693002";

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(testRecipient, testMessageText, 1);
        String expected = "Message ready to send.";
        String actual = msg.checkMessageLength();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageLengthExceeds250() {
        // Using a loop to create a 260-character string to ensure it works on all Java versions
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            sb.append("a");
        }
        String longMessage = sb.toString();
        
        Message msg = new Message(testRecipient, longMessage, 1);
        
        String expected = "Message exceeds 250 characters by 10; please reduce the size.";
        String actual = msg.checkMessageLength();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientNumberSuccess() {
        Message msg = new Message(testRecipient, testMessageText, 1);
        String expected = "Cell phone number successfully captured.";
        String actual = msg.checkRecipientCell();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientNumberFailure() {
        Message msg = new Message("08575975889", testMessageText, 1);
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String actual = msg.checkRecipientCell();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message(testRecipient, testMessageText, 0);
        
        String expectedHash;
        expectedHash = "00:0:HITONIGHT?";
        String actualHash = msg.getMessageHash();
        
        assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testMessageIDCreated() {
        Message msg = new Message(testRecipient, testMessageText, 1);
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testSentMessageSend() {
        Message msg = new Message(testRecipient, testMessageText, 1);
        String expected = "Message successfully sent.";
        String actual = msg.SentMessage(1);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message(testRecipient, testMessageText, 1);
        String expected = "Press 0 to delete the message.";
        String actual = msg.SentMessage(2);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSentMessageStore() {
        Message msg = new Message(testRecipient, testMessageText, 1);
        String expected = "Message successfully stored.";
        String actual = msg.SentMessage(3);
        
        assertEquals(expected, actual);
    }
}