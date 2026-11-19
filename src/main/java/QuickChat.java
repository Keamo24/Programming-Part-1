/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 27627
 */
import java.util.Scanner;

public class QuickChat {
    
    public void startChat(Scanner scanner) {
        System.out.println("Welcome to QuickChat.");
        
        // Load the test data into the arrays at the start
        MessageManager.populateTestData();
        
        System.out.print("How many messages do you wish to enter? ");
        int numMessages = Integer.parseInt(scanner.nextLine());

        int messagesSent = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages"); // NEW OPTION
            System.out.print("Choose an option: ");
            String menuChoice = scanner.nextLine();

            switch (menuChoice) {
                case "1":
                    if (messagesSent >= numMessages) {
                        System.out.println("You have reached your message limit.");
                        break;
                    }
                    
                    // Collect Message Data
                    System.out.print("Enter Recipient Cell Number (+...): ");
                    String recipient = scanner.nextLine();
                    
                    System.out.print("Enter Message (max 250 chars): ");
                    String text = scanner.nextLine();

                    // Create Message Object
                    Message msg = new Message(recipient, text, messagesSent + 1);

                    // Validate
                    String cellStatus = msg.checkRecipientCell();
                    String lengthStatus = msg.checkMessageLength();

                    System.out.println("\n" + cellStatus);
                    System.out.println(lengthStatus);

                    // Only proceed if both are valid
                    if (cellStatus.equals("Cell phone number successfully captured.") && 
                        lengthStatus.equals("Message ready to send.")) {
                        
                        System.out.println("Message ID generated: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getMessageHash());
                        
                        System.out.println("\nChoose action:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");
                        System.out.print("Choice: ");
                        int action = Integer.parseInt(scanner.nextLine());
                        
                        System.out.println(msg.SentMessage(action));
                        
                        // --- UPDATED LOGIC TO POPULATE ARRAYS ---
                        if (action == 1) { 
                            messagesSent++;
                            MessageManager.sentMessages.add(msg);
                            MessageManager.messageIDs.add(msg.getMessageID());
                            MessageManager.messageHashes.add(msg.getMessageHash());
                        } else if (action == 2) {
                            MessageManager.disregardedMessages.add(msg);
                            MessageManager.messageIDs.add(msg.getMessageID());
                            MessageManager.messageHashes.add(msg.getMessageHash());
                        } else if (action == 3) {
                            MessageManager.storedMessages.add(msg);
                            MessageManager.messageIDs.add(msg.getMessageID());
                            MessageManager.messageHashes.add(msg.getMessageHash());
                            
                            // Save to JSON file!
                            MessageManager.saveStoredMessagesToJSON();
                        }
                    }
                    break;

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    System.out.println("Total messages sent: " + messagesSent);
                    System.out.println("Exiting QuickChat. Goodbye!");
                    running = false;
                    break;
                    
                case "4":
                    // --- NEW SUB-MENU FOR PART 3 ---
                    boolean inSubMenu = true;
                    while (inSubMenu) {
                        System.out.println("\n--- Stored Messages Menu ---");
                        System.out.println("a) Display sender and recipient");
                        System.out.println("b) Display longest message");
                        System.out.println("c) Search for a message ID");
                        System.out.println("d) Search messages for a particular recipient");
                        System.out.println("e) Delete a message using the message hash");
                        System.out.println("f) Display full report");
                        System.out.println("0) Back to Main Menu");
                        System.out.print("Choose an option: ");
                        String subChoice = scanner.nextLine();

                        switch (subChoice.toLowerCase()) {
                            case "a":
                                System.out.println(MessageManager.displaySenderAndRecipient());
                                break;
                            case "b":
                                System.out.println("Longest Message: " + MessageManager.getLongestMessage());
                                break;
                            case "c":
                                System.out.print("Enter Message ID to search: ");
                                String searchID = scanner.nextLine();
                                System.out.println("Result: " + MessageManager.searchByID(searchID));
                                break;
                            case "d":
                                System.out.print("Enter Recipient to search (+...): ");
                                String searchRec = scanner.nextLine();
                                System.out.println("Results:\n" + MessageManager.searchByRecipient(searchRec));
                                break;
                            case "e":
                                System.out.print("Enter Message Hash to delete: ");
                                String deleteHash = scanner.nextLine();
                                System.out.println(MessageManager.deleteByHash(deleteHash));
                                // Update JSON file after deletion so it stays in sync
                                MessageManager.saveStoredMessagesToJSON();
                                break;
                            case "f":
                                System.out.println(MessageManager.displayReport());
                                break;
                            case "0":
                                inSubMenu = false;
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}