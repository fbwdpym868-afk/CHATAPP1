# ChatApp — README

A Java console application that simulates a basic messaging platform. It includes user registration, login, message creation, validation, hashing, and JSON-based message storage. The project is built around the **PROG5121** programming assignment requirements.

---

## Overview

ChatApp allows a user to:

1. **Register** with a first name, last name, username, password, and South African cell phone number, all validated against strict formatting rules.
2. **Log in** using their registered credentials.
3. **Send messages** by entering a recipient cell number and message text, then choosing to **Send**, **Store** (to a JSON file), or **Disregard** each message.
4. **View recently sent messages** (currently a placeholder displaying "Coming Soon").

Every message is validated, assigned a unique 10-digit ID, and given a **message hash** used for identification.

---

## Features

### Part 1 — Registration and Login

- **Username validation:** Must contain an underscore (`_`) and be **5 characters or fewer**.
- **Password complexity:** Must contain at least **8 characters**, one **capital letter**, one **number**, and one **special character**.
- **Cell phone validation:** Must start with `+27` followed by exactly 9 digits (e.g., `+27838968976`).
- **Login status messages:** Displays a welcome message on successful login or a retry prompt on failure. Login retries continue until successful.

### Part 2 — Messaging

- **Recipient validation:** Must start with `+` and contain **10–12 digits**.
- **Message length validation:** Must be non-empty and contain no more than **250 characters**.
- **Unique message ID:** A random 10-digit string, with the first digit between 1 and 9.
- **Message hash:** Format: `firstTwoIDchars:messageNumber:FIRSTWORDLASTWORD` (uppercase, letters only).

  Example: `00:0:HITONIGHT`

- **Message actions:** `Send` (marked Sent), `Store` (saved to `messages.json`), or `Disregard`.
- **Persistent storage:** Stored messages are appended to `messages.json` using the Google Gson library.

---

## Project Structure

```text
chatapp/
├── ChatApp.java          # Entry point — registration, login, and main menu
├── Login.java            # Registration and login validation logic
├── Message.java          # Message model, validation, ID, and hash generation
├── MessageStorage.java   # JSON loading and saving using Gson
└── ChatAppTest.java      # JUnit 4 test suite (Parts 1 and 2)
```

A `messages.json` file is created automatically in the project root the first time a message is stored.

---

## Requirements

- **Java JDK 8 or higher**
- **Google Gson** library for JSON serialization
- **JUnit 4** for running the tests

If you are using Maven, add these dependencies to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.11.0</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

If you are using a plain IDE project in IntelliJ IDEA, Eclipse, or NetBeans, add the **Gson** and **JUnit 4** JAR files to your project's build path manually.

---

## How to Run

1. **Clone or download** the project source files into a package folder named `chatapp`.
2. **Compile** the files:

   ```bash
   javac -cp "gson-2.11.0.jar" chatapp/*.java
   ```

3. **Run** the main class:

   ```bash
   java -cp ".:gson-2.11.0.jar" chatapp.ChatApp
   ```

   On Windows, replace `:` with `;` in the classpath.

4. Follow the on-screen prompts:
   - Register with a valid username, password, and South African cell phone number.
   - Log in using your registered credentials.
   - Choose `1` to send messages, `2` to view recent messages, or `3` to quit.

---

## Running the Tests

The `ChatAppTest.java` file contains 22 unit tests covering Parts 1 and 2.

### Using an IDE

- **Eclipse:** Right-click `ChatAppTest.java` and select **Run As → JUnit Test**.
- **IntelliJ IDEA:** Right-click `ChatAppTest.java` and select **Run 'ChatAppTest'**.

### Using the Command Line (JUnit 4)

```bash
javac -cp ".:gson-2.11.0.jar:junit-4.13.2.jar:hamcrest-core-1.3.jar" chatapp/*.java

java -cp ".:gson-2.11.0.jar:junit-4.13.2.jar:hamcrest-core-1.3.jar" org.junit.runner.JUnitCore chatapp.ChatAppTest
```

### Test Coverage

| Area | Number of Tests |
|---|---:|
| Registration (valid/invalid username and password) | 3 |
| Cell phone validation and messages | 3 |
| Login status messages | 2 |
| Field validation (username, password, phone) | 6 |
| Login success and failure | 2 |
| Recipient validation | 2 |
| Message length validation | 2 |
| Message hash (official test data) | 1 |
| Message ID length | 1 |
| **Total** | **22** |

---

## Example Walkthrough

### Registration

```text
Enter username (must contain '_' and be 5 characters or fewer, e.g. kyl_1): kyl_1
Username successfully captured.
Enter password (min 8 chars, 1 capital, 1 number, 1 special): Ch&&sec@ke99!
Password successfully captured.
Enter SA cell phone number (must start with +27): +27838968976
Cell phone number successfully added.
```

### Messaging

```text
Recipient cell number: +27718693002
Message text: Hi Mike, can you join us for dinner tonight?

Message ID: 0012345678
Message Hash: 00:0:HITONIGHT
Recipient: +27718693002
Message: Hi Mike, can you join us for dinner tonight?

1) Send Message
2) Store Message
3) Disregard Message
```

---

## Design Notes

- **`Message` constructors:** The class uses two constructors. The standard constructor generates a random ID, while the overloaded constructor accepts a specific ID. The overloaded constructor is used for deterministic testing, such as matching the official `00:0:HITONIGHT` hash.
- **`MessageStorage`:** Wraps all Gson operations. If `messages.json` does not exist, `loadMessages()` gracefully returns an empty list so that `saveMessage()` can work from a clean state.
- **Message `flag`:** Tracks the message lifecycle: `Pending` → `Sent`, `Stored`, or `Disregard`.
- **`totalMessagesProcessed`:** Counts messages that were either sent or stored. Disregarded messages are not counted.

---

## Attributions

- **Google. (2024).** *Gson User Guide.* Available at: https://github.com/google/gson/blob/main/UserGuide.md

  Used for JSON serialization and deserialization in `MessageStorage.java`.

- **JUnit 4:** Testing framework used in `ChatAppTest.java`.

---

## Possible Future Improvements

- Implement the **Show Recently Sent Messages** option, which currently displays `Coming Soon`.
- Add a message history viewer that reads previously stored messages from `messages.json`.
- Encrypt or securely hash stored passwords.
- Add a graphical user interface (GUI) using JavaFX or Swing.
- Add more unit tests for `MessageStorage`, including save/load round-trip tests.
- Support international phone number formats beyond `+27`.

---

## License

This project was created for educational purposes as part of a programming assignment. It is free to use and modify for learning.

