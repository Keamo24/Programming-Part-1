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
        
        System.out.print("How many messages do you wish to enter? ");
        int numMessages = Integer.parseInt(scanner.nextLine());

        int messagesSent = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
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
                        
                        if (action == 1) { // Only count as sent if they choose Send
                            messagesSent++;
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

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}