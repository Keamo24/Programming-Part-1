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
import java.util.Scanner;

public class MessageManager {
    
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();

    public static void populateTestData() {
        // Clear existing data just in case
        sentMessages.clear(); storedMessages.clear(); disregardedMessages.clear();
        
        sentMessages.add(new Message("+27834557896", "Did you get the cake?", 1));
        storedMessages.add(new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 2));
        disregardedMessages.add(new Message("+27834484567", "Yohoooo, I am at your gate.", 3));
        sentMessages.add(new Message("0838884567", "It is dinner time!", 4)); 
        storedMessages.add(new Message("+27838884567", "Ok, I am leaving without you.", 5));
        
        for(Message m : sentMessages) { messageIDs.add(m.getMessageID()); messageHashes.add(m.getMessageHash()); }
        for(Message m : storedMessages) { messageIDs.add(m.getMessageID()); messageHashes.add(m.getMessageHash()); }
        for(Message m : disregardedMessages) { messageIDs.add(m.getMessageID()); messageHashes.add(m.getMessageHash()); }
    }

    // --- NEW JSON METHODS ---

    // Writes stored messages to a text file formatted like JSON
    public static void saveStoredMessagesToJSON() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("stored_messages.json"))) {
            writer.println("[");
            for (int i = 0; i < storedMessages.size(); i++) {
                writer.print(storedMessages.get(i).toJSON());
                if (i < storedMessages.size() - 1) writer.println(",");
            }
            writer.println("\n]");
        } catch (IOException e) {
            System.out.println("Error saving to JSON: " + e.getMessage());
        }
    }

    // Reads the JSON file to populate the Stored Messages array
    public static void loadStoredMessagesFromJSON() {
        // For a beginner assignment, reading strict JSON without libraries is hard.
        // We will simulate reading the file line by line and extracting data.
        try (Scanner scanner = new Scanner(new File("stored_messages.json"))) {
            String currentText = "";
            String currentRecipient = "";
            int counter = 1;
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.contains("\"recipient\":")) {
                    currentRecipient = line.split("\"")[3];
                } else if (line.contains("\"messageText\":")) {
                    currentText = line.split("\"")[3];
                } else if (line.equals("}") || line.equals("},")) {
                    // Create message when we hit the end of a block
                    storedMessages.add(new Message(currentRecipient, currentText, counter++));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved JSON file found. Loading default test data.");
        }
    }

    // --- UPDATED OPTION 4 LOGIC (Returns Strings for Testing) ---

    public static String displaySenderAndRecipient() {
        StringBuilder sb = new StringBuilder("--- Stored Messages ---\n");
        for (Message m : storedMessages) {
            sb.append("Sender: Developer | Recipient: ").append(m.getRecipient()).append("\n");
        }
        return sb.toString();
    }

    public static String getLongestMessage() {
        String longest = "";
        for (Message m : storedMessages) {
            if (m.getMessageText().length() > longest.length()) {
                longest = m.getMessageText();
            }
        }
        return longest;
    }

    public static String searchByID(String id) {
        for (Message m : storedMessages) {
            if (m.getMessageID().equals(id)) {
                return m.getMessageText(); // Returns just the message text for the test
            }
        }
        return "Message ID not found.";
    }

    public static String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (Message m : storedMessages) {
            if (m.getRecipient().equals(recipient)) {
                sb.append(m.getMessageText()).append("\n");
            }
        }
        return sb.length() > 0 ? sb.toString().trim() : "No messages found.";
    }

    public static String deleteByHash(String hash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                String deletedText = storedMessages.get(i).getMessageText();
                storedMessages.remove(i);
                return "Message: \"" + deletedText + "\" successfully deleted.";
            }
        }
        return "Hash not found.";
    }

    public static String displayReport() {
        StringBuilder sb = new StringBuilder("--- Report ---\n");
        for (Message m : storedMessages) {
            sb.append("Hash: ").append(m.getMessageHash())
              .append(" | Recipient: ").append(m.getRecipient())
              .append(" | Message: ").append(m.getMessageText()).append("\n");
        }
        return sb.toString();
    }
}
