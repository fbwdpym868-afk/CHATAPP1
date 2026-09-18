package chatapp;



import java.util.regex.Pattern;

public class Login {

    private String storedFirstName;
    private String storedLastName;
    private String storedUsername;
    private String storedPassword;
    private String storedCellPhone;

    // Check the username
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Check the password
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    // Check the phone number
    // Regular expression adapted from the ITU-T E.164 international
    // phone number standard (https://www.itu.int/rec/T-REC-E.164).
    public boolean checkCellPhoneNumber(String cellPhone) {
        return Pattern.matches("^\\+27[0-9]{9}$", cellPhone);
    }

    // Returns the registration message
    public String registerUser(String username, String password) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        return "User registered successfully.";
    }

    // Returns the cell phone check message used by the tests
    public String getCellPhoneMessage(String cellPhone) {
        if (checkCellPhoneNumber(cellPhone)) {
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
    }

    // Check the login details
    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    // Returns the login status message
    public String returnLoginStatus(boolean loginSuccess, String firstName, String lastName) {
        if (loginSuccess) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Store the user details
    public void setStoredDetails(String firstName, String lastName, String username,
                                 String password, String cellPhone) {
        this.storedFirstName = firstName;
        this.storedLastName = lastName;
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellPhone = cellPhone;
    }

    public String getStoredFirstName() {
        return storedFirstName;
    }

    public String getStoredLastName() {
        return storedLastName;
    }

    public String getStoredUsername() {
        return storedUsername;
    }

    public String getStoredPassword() {
        return storedPassword;
    }

    public String getStoredCellPhone() {
        return storedCellPhone;
    }
}