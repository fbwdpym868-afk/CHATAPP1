package chatapp;

import java.util.regex.Pattern;

public class Login {
    // array spots: 0=first, 1=last, 2=user, 3=pass, 4=cell
    private static final int FIRST_NAME = 0;
    private static final int LAST_NAME  = 1;
    private static final int USERNAME   = 2;
    private static final int PASSWORD   = 3;
    private static final int CELL       = 4;

    // regex for +27 and 9 digits
    private static final Pattern SA_MOBILE = Pattern.compile("^\\+27[0-9]{9}$");
    private final String[] savedUser = new String[5];

    // checking if there's a _ and it's 5 chars or less
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // checking 8 chars, capital, number, special char
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;
        boolean hasCapital = false, hasDigit = false, hasSymbol = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) hasCapital = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSymbol = true;
        }
        return hasCapital && hasDigit && hasSymbol;
    }

    // checking if it matches the +27 pattern
    public boolean checkCellPhoneNumber(String cellPhone) {
        return SA_MOBILE.matcher(cellPhone).matches();
    }

    // putting the right messages together based on checks
    public String registerUser(String username, String password) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        return "Username successfully captured.\nPassword successfully captured.";
    }

    // putting user info into the array
    public void saveUser(String firstName, String lastName, String username, String password, String cellPhone) {
        savedUser[FIRST_NAME] = firstName;
        savedUser[LAST_NAME]  = lastName;
        savedUser[USERNAME]   = username;
        savedUser[PASSWORD]   = password;
        savedUser[CELL]       = cellPhone;
    }

    // matching what they typed to what's in the array
    public boolean loginUser(String username, String password) {
        if (savedUser[USERNAME] == null) return false;
        return savedUser[USERNAME].equals(username) && savedUser[PASSWORD].equals(password);
    }

    // returning welcome or error text
    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "Welcome " + savedUser[FIRST_NAME] + ", " + savedUser[LAST_NAME] + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    // grabbing full name for the part 3 report
    public String getFullName() {
        return savedUser[FIRST_NAME] + " " + savedUser[LAST_NAME];
    }
}