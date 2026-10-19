/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 27627
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Login loginSystem = new Login();
            
            System.out.println("=====================================");
            System.out.println("       WELCOME TO QUICKCHAT          ");
            System.out.println("=====================================");
            
            // --- PART 1: REGISTRATION ---
            System.out.println("\n--- Registration ---");
            System.out.print("Enter First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lName = scanner.nextLine();
            System.out.print("Enter Username (must have underscore, max 5 chars): ");
            String uName = scanner.nextLine();
            System.out.print("Enter Password (min 8 chars, 1 capital, 1 number, 1 special): ");
            String pass = scanner.nextLine();
            System.out.print("Enter Cell Phone (+27...): ");
            String cell = scanner.nextLine();
            
            // Call the registerUser method from your Login class
            String registrationMessage = loginSystem.registerUser(uName, pass, cell, fName, lName);
            System.out.println("\nRegistration Status:\n" + registrationMessage);
            
            // --- PART 2: LOGIN & MESSAGING ---
            // Only proceed to login if registration was successful
            if (registrationMessage.contains("successfully captured")) {
                
                System.out.println("\n--- Login ---");
                System.out.print("Enter Username: ");
                String loginUser = scanner.nextLine();
                System.out.print("Enter Password: ");
                String loginPass = scanner.nextLine();
                
                // Check credentials
                boolean isLoggedIn = loginSystem.loginUser(loginUser, loginPass);
                System.out.println("\nLogin Status:\n" + loginSystem.returnLoginStatus(isLoggedIn));
                
                // If login works, start the chat menu!
                if (isLoggedIn) {
                    QuickChat chatApp = new QuickChat();
                    chatApp.startChat(scanner);
                } else {
                    System.out.println("Access Denied. You must log in successfully to send messages.");
                }
                
            } else {
                System.out.println("\nRegistration failed. Cannot proceed to login.");
            }
        }
    }
}