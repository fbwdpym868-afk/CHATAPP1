package chatapp;

import java.util.Scanner;
     

public class ChatApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("========== ChatApp ==========");
        System.out.println("========== Registration ==========");

        // Get first name
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine().trim();

        // Get last name
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine().trim();

        // Get username and check it
        String username;
        while (true) {
            System.out.print("Enter username (must contain '_' and be 5 characters or fewer, e.g. kyl_1): ");
            username = scanner.nextLine().trim();
            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        // Get password and check it
        String password;
        while (true) {
            System.out.print("Enter password (min 8 chars, 1 capital, 1 number, 1 special, e.g. Ch&&sec@ke99!): ");
            password = scanner.nextLine();
            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        // Get cell phone number and check it
        String cellPhone;
        while (true) {
            System.out.print("Enter SA cell phone number (must start with +27, e.g. +27838968976): ");
            cellPhone = scanner.nextLine().trim();
            if (login.checkCellPhoneNumber(cellPhone)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        // Store the user details
        login.setStoredDetails(firstName, lastName, username, password, cellPhone);
        System.out.println(login.registerUser(username, password));
        System.out.println("You can now log in with your username and password.");

        // Login section - allow retries until successful
        System.out.println();
        System.out.println("========== Login ==========");
        boolean loginSuccess = false;
        while (!loginSuccess) {
            System.out.print("Enter your username: ");
            String loginUsername = scanner.nextLine().trim();
            System.out.print("Enter your password: ");
            String loginPassword = scanner.nextLine();

            loginSuccess = login.loginUser(loginUsername, loginPassword);
            System.out.println(login.returnLoginStatus(loginSuccess, login.getStoredFirstName(), login.getStoredLastName()));

            if (!loginSuccess) {
                System.out.println("Please try again.\n");
            }
        }

        scanner.close();
    }
}