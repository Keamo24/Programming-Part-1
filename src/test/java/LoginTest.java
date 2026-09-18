/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // Instantiate the Login class
    Login login = new Login();
    
    // Setup mock data for login tests
    String testFirstName = "Kyle";
    String testLastName = "Smith";

    // --- assertEquals Tests (For registerUser and validation strings) ---

    @Test
    public void testUsernameCorrectlyFormatted() {
        // Test Data: "kyl_1"
        boolean result = login.checkUserName("kyl_1");
        assertTrue("Username should be correctly formatted", result);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        // Test Data: "kyle!!!!!!"
        boolean result = login.checkUserName("kyle!!!!!!");
        assertFalse("Username should be incorrectly formatted", result);
    }

    @Test
    public void testPasswordMeetsComplexity() {
        // Test Data: "Ch&&sec@ke99!"
        boolean result = login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue("Password should meet complexity requirements", result);
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        // Test Data: "password"
        boolean result = login.checkPasswordComplexity("password");
        assertFalse("Password should not meet complexity requirements", result);
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        // Test Data: +27838968976
        boolean result = login.checkCellPhoneNumber("+27838968976");
        assertTrue("Cell phone number should be correctly formatted", result);
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        // Test Data: 08966553
        boolean result = login.checkCellPhoneNumber("08966553");
        assertFalse("Cell phone number should be incorrectly formatted", result);
    }

    // --- assertEquals Tests (For registerUser messages) ---

    @Test
    public void testRegisterUserSuccess() {
        String expected = "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", testFirstName, testLastName);
        assertEquals(expected, actual);
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = login.registerUser("kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976", testFirstName, testLastName);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testRegisterUserInvalidPassword() {
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = login.registerUser("kyl_1", "password", "+27838968976", testFirstName, testLastName);
        assertEquals(expected, actual);
    }

    @Test
    public void testRegisterUserInvalidCell() {
        String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553", testFirstName, testLastName);
        assertEquals(expected, actual);
    }

    // --- assertTrue/False Tests (For Login) ---

    @Test
    public void testLoginSuccessful() {
        // Register a user first to populate the stored credentials
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", testFirstName, testLastName);
        
        // Attempt login with correct credentials
        boolean isLoggedIn = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue("Login should be successful", isLoggedIn);
        
        // Verify the message
        String expectedMessage = "Welcome " + testFirstName + ", " + testLastName + " it is great to see you again.";
        assertEquals(expectedMessage, login.returnLoginStatus(isLoggedIn));
    }

    @Test
    public void testLoginFailed() {
        // Register a user
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", testFirstName, testLastName);
        
        // Attempt login with incorrect credentials
        boolean isLoggedIn = login.loginUser("kyl_1", "WrongPassword123!");
        assertFalse("Login should fail", isLoggedIn);
        
        // Verify the message
        String expectedMessage = "Username or password incorrect, please try again.";
        assertEquals(expectedMessage, login.returnLoginStatus(isLoggedIn));
    }

    private void assertTrue(String username_should_be_correctly_formatted, boolean result) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertFalse(String cell_phone_number_should_be_incorrectly_f, boolean result) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}