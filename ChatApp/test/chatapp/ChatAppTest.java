package chatapp;

import chatapp.Login;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChatAppTest {

    private Login login = new Login();

    // assertEquals tests

    @Test
    public void testRegisterUserWithValidDetails() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("User registered successfully.", result);
    }

    @Test
    public void testRegisterUserWithInvalidUsername() {
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testRegisterUserWithInvalidPassword() {
        String result = login.registerUser("kyl_1", "password");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testCellPhoneValidMessage() {
        String result = login.getCellPhoneMessage("+27838968976");
        assertEquals("Cell number successfully captured.", result);
    }

    @Test
    public void testCellPhoneInvalidMessage() {
        String result = login.getCellPhoneMessage("08966553");
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.", result);
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        String result = login.returnLoginStatus(true, "John", "Doe");
        assertEquals("Welcome John, Doe it is great to see you again.", result);
    }

    @Test
    public void testReturnLoginStatusFailure() {
        String result = login.returnLoginStatus(false, "John", "Doe");
        assertEquals("Username or password incorrect, please try again.", result);
    }

    // assertTrue / assertFalse tests

    @Test
    public void testValidUsername() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testInvalidUsername() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testValidCellPhone() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testInvalidCellPhone() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testSuccessfulLogin() {
        login.setStoredDetails("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testFailedLogin() {
        login.setStoredDetails("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("wrong", "wrong"));
    }
}