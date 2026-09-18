/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 27627
 */
/**
 * Login Class for Registration and Authentication
 * 1. Oracle. (2023). Java Regular Expressions. Available at: https://docs.oracle.com/javase/tutorial/essential/regex/
 * 2. 
 */
public class Login {

    // Fields to store user details
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    // Default constructor
    public Login() {
    }

    // --- Registration Methods ---

    /**
     * Checks if the username contains an underscore and is no more than 5 characters.
     * @param username The username to check.
     * @return true if valid, false otherwise.
     */
    public boolean checkUserName(String username) {
        // Regex: Contains at least one underscore, and is 1 to 5 characters long total
        if (username != null && username.matches("^(?=.{1,5}$).*_.*$")) {
            this.username = username;
            return true;
        }
        return false;
    }

    /**
     * Checks if the password meets complexity requirements.
     * At least 8 chars, 1 capital, 1 number, 1 special char.
     * @param password The password to check.
     * @return true if valid, false otherwise.
     */
    public boolean checkPasswordComplexity(String password) {
        // Regex: (?=.*[A-Z]) ensures capital, (?=.*[0-9]) ensures number, 
        // (?=.*[^a-zA-Z0-9]) ensures special char, .{8,} ensures length
        if (password != null && password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$")) {
            this.password = password;
            return true;
        }
        return false;
    }

    /**
     * Checks if the cell phone number contains the international code (+27) 
     * and is no more than 10 characters long.
     * @param phoneNumber The cell phone number to check.
     * @return true if valid, false otherwise.
     */
    public boolean checkCellPhoneNumber(String phoneNumber) {
        // Regex: Starts with +27, followed by 9 digits (Total 12 chars). 
        // However, the prompt says "no more than ten characters long". 
        // For a SA number +27 followed by 9 digits is 12 chars. 
        // We will use a strict regex for SA international format: +27 followed by 9 digits.
        // If it must be strictly 10 chars, it would be +27XXXXXXXX (12 chars). 
        // Let's assume the prompt means the digits after the code, or a specific format.
        // Standard SA format: +27 followed by 9 digits.
        if (phoneNumber != null && phoneNumber.matches("^\\+27[0-9]{9}$")) {
            this.cellPhoneNumber = phoneNumber;
            return true;
        }
        return false;
    }

    /**
     * Registers the user by validating all fields and returning the appropriate message.
     * @param username The provided username.
     * @param password The provided password.
     * @param cellPhoneNumber The provided cell phone number.
     * @param firstName User's first name.
     * @param lastName User's last name.
     * @return A string message indicating the registration status.
     */
    public String registerUser(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        // Check Username
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        // Check Password
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        // Check Cell Phone
        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        // If all pass
        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
    }

    // --- Login Methods ---

    /**
     * Verifies that the login details match the stored registration details.
     * @param enteredUsername The username entered at login.
     * @param enteredPassword The password entered at login.
     * @return true if credentials match, false otherwise.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        // In a real app, you'd hash the password. For this assignment, we compare strings.
        return this.username != null && this.username.equals(enteredUsername) 
                && this.password != null && this.password.equals(enteredPassword);
    }

    /**
     * Returns the login status message.
     * @param loggedIn The boolean result from loginUser().
     * @return Success or Failure message.
     */
    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
    
    
     