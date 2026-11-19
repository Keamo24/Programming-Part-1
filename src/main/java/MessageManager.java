/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 27627
 */
import java.io.*;
import java.util.ArrayList;

public class MessageManager {
    
    // The arrays required by the assignment
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();
    
    // You can extract IDs and Hashes from the Message objects, but we'll keep track of them here too
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();

    // 1. Method to populate arrays with the Test Data from the brief
    public static void populateTestData() {
        // Message 1 (Sent)
        Message msg1 = new Message("+27834557896", "Did you get the cake?", 1);
        sentMessages.add(msg1);
        
        // Message 2 (Stored)
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 2);
        storedMessages.add(msg2);
        
        // Message 3 (Disregard)
        Message msg3 = new Message("+27834484567", "Yohoooo, I am at your gate.", 3);
        disregardedMessages.add(msg3);

        // Message 4 (Sent)
        Message msg4 = new Message("0838884567", "It is dinner time!", 4); 
        sentMessages.add(msg4); // Placed in sent based on test data requirement
        
        // Message 5 (Stored)
        Message msg5 = new Message("+27838884567", "Ok, I am leaving without you.", 5);
        storedMessages.add(msg5);
        
        // Collect all IDs and Hashes for the arrays
        for(Message m : sentMessages) { messageIDs.add(m.getMessageID()); messageHashes.add(m.getMessageHash()); }
        for(Message m : storedMessages) { messageIDs.add(m.getMessageID()); messageHashes.add(m.getMessageHash()); }
        for(Message m : disregardedMessages) { messageIDs.add(m.getMessageID()); messageHashes.add(m.getMessageHash()); }
    }

    // --- OPTION 4 LOGIC ---

    // a. Display sender and recipient
    public static void displaySenderAndRecipient() {
        System.out.println("\n--- Stored Messages: Senders and Recipients ---");
        for (Message m : storedMessages) {
            System.out.println("Sender: Developer | Recipient: " + m.getRecipient());
        }
    }

    // b. Display longest message
    public static String getLongestMessage() {
        String longest = "";
        for (Message m : storedMessages) {
            if (m.getMessageText().length() > longest.length()) {
                longest = m.getMessageText();
            }
        }
        System.out.println("\nLongest Message: " + longest);
        return longest;
    }

    // c. Search by Message ID
    public static void searchByID(String id) {
        System.out.println("\n--- Search Results for ID: " + id + " ---");
        for (Message m : storedMessages) {
            if (m.getMessageID().equals(id)) {
                System.out.println("Recipient: " + m.getRecipient() + " | Message: " + m.getMessageText());
                return;
            }
        }
        System.out.println("Message ID not found.");
    }

    // d. Search by Recipient
    public static void searchByRecipient(String recipient) {
        System.out.println("\n--- Search Results for Recipient: " + recipient + " ---");
        boolean found = false;
        for (Message m : storedMessages) {
            if (m.getRecipient().equals(recipient)) {
                System.out.println("Message: " + m.getMessageText());
                found = true;
            }
        }
        if(!found) System.out.println("No messages found for this recipient.");
    }

    // e. Delete by Message Hash
    public static void deleteByHash(String hash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                System.out.println("Message: \"" + storedMessages.get(i).getMessageText() + "\" successfully deleted.");
                storedMessages.remove(i);
                return;
            }
        }
        System.out.println("Hash not found.");
    }

    // f. Display Full Report
    public static void displayReport() {
        System.out.println("\n--- FULL STORED MESSAGES REPORT ---");
        for (Message m : storedMessages) {
            System.out.println("Hash: " + m.getMessageHash());
            System.out.println("Recipient: " + m.getRecipient());
            System.out.println("Message: " + m.getMessageText());
            System.out.println("-----------------------------------");
        }
    }
}
