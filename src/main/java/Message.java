/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 27627
 */

import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    public Message(String recipient, String messageText, int messageNumber) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Auto-generate a 10-digit Message ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }
    // Check if Message ID is valid (not more than 10 chars)
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    // Check if Recipient Cell is valid (starts with + and is <= 10 chars)
    public String checkRecipientCell() {
        // Note: Your brief says <= 10 chars and starts with a code.
        if (recipient != null && recipient.startsWith("+") && recipient.length() <= 12) { 
            // I used 12 here because SA numbers (+27...) are usually 12 chars. 
            // If your lecturer strictly wants 10, change 12 to 10.
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    // Create the Message Hash (e.g., 00:0:HITONIGHT)
    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) return "";
        
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", ""); // Remove punctuation
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        
        String firstTwoID = messageID.substring(0, 2);
      
        // Format: First2ID:MessageNumber:FIRSTWORDLASTWORD
        return (firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }
    // Check if message is not more than 250 characters
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }
    // Handle the Send, Disregard, Store logic
    public String SentMessage(int choice) {
        return switch (choice) {
            case 1 -> "Message successfully sent.";
            case 2 -> "Press 0 to delete the message.";
            case 3 -> "Message successfully stored.";
            default -> "Invalid choice.";
        };
    }
    // Research method (Placeholder for now)
    public String storeMessage() {
        // This is where you would write JSON logic later
        return "Message successfully stored in JSON (Simulated).";
    }
    // Getters for printing
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
}