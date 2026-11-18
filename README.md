# ChatApp - Full POE (Parts 1, 2 & 3)

## What is this app?
This is a console-based chat application built in Java. It handles registering a user, logging in, sending messages, and managing a list of stored messages. It is built step-by-step to match the rubric for all three parts of the POE.

## Part 1 - Registration and Login
This is the starting point of the app where users get into the system.
*   **Username Check:** I made sure the username needs an underscore (`_`) and can't be longer than 5 characters.
*   **Password Check:** The password has to be at least 8 characters long, have one capital letter, one number, and one special character.
*   **Cell Phone Check:** I used a regular expression (regex) to make sure the cell number starts with `+27` and has exactly 9 digits after that.
*   **Login:** Once the user registers, their details are saved. They have to type the exact same username and password to log in. If they get it wrong, the app keeps asking them until they get it right.

## Part 2 - The QuickChat Menu and Messages
Once logged in, the user gets to the main menu.
*   **Main Menu:** The user can choose Option 1 (Send Messages), Option 2 (Show recently sent - which just says "Coming Soon."), or Option 3 (Quit). The app loops here until they quit.
*   **Sending Messages:** The app asks how many messages they want to enter.
*   **Message Details:** For each message, they enter a recipient cell number and the message text (which can't be over 250 characters).
*   **Message ID & Hash:** Every message gets a random 10-digit ID. The hash is made by taking the first two digits of the ID, adding a colon, the message number, another colon, and the first and last word of the message in all caps (e.g., `00:0:HITHANKS`).
*   **Choices:** For each message, the user can choose to Send it, Store it (which saves it to a JSON file), or Disregard it.

## Part 3 - Stored Messages and Arrays
This is the final part where I added the data management and reporting features.
*   **Parallel Arrays:** I set up arrays to hold all the sent, stored, and disregarded messages, plus the IDs, hashes, and recipients. This keeps the data organized without hard-coding.
*   **JSON Reading:** The app reads the `messages.json` file and loads the data into the arrays so we can search through it.
*   **Menu Option 4 (Stored Messages):** I added a fourth menu option with a sub-menu that lets the user:
    1.  See the sender and recipient of everything stored.
    2.  Find the longest message by looping through the array.
    3.  Search for a message ID to see the recipient and text.
    4.  Search for a recipient to see all messages sent to them.
    5.  Delete a message using its unique hash (which removes it from the JSON file and the array).
    6.  View a full report of all stored messages showing the Hash, Recipient, and Message.

## Unit Testing (JUnit)
I wrote tests for every part to make sure everything works.
*   **Part 1 Tests:** Checking good/bad usernames, passwords, and cell numbers, plus testing successful and failed logins.
*   **Part 2 Tests:** Checking message length limits, recipient formats, and making sure the hash generates correctly (like `00:0:HITONIGHT`).
*   **Part 3 Tests:** I used the exact test data from the rubric to check that the arrays are populated correctly, that the longest message is found, that searching by ID and recipient works, and that the delete message prints the exact success string required.

### Test Data Used for Part 3 (From Rubric)
*   **Message 1 (Sent):** `+27834557896`, "Did you get the cake?"
*   **Message 2 (Stored):** `+27838884567`, "Where are you? You are late! I have asked you to be on time."
*   **Message 3 (Disregard):** `+27834484567`, "Yohoooo, I am at your gate."
*   **Message 4 (Sent):** `0838884567`, "It is dinner time !"
*   **Message 5 (Stored):** `+27838884567`, "Ok, I am leaving without you."

## How to Run
1. Open the project in your Java IDE (like NetBeans).
2. Make sure the Gson library is added to the classpath.
3. Run `ChatApp.java` in the `chatapp` package.
4. Follow the console prompts.

## Attribution
*   **Gson Library:** Used for handling the JSON files.
    *   Google, 2024. *Gson User Guide*.
    *   Available at: https://github.com/google/gson/blob/main/UserGuide.md
    *   
