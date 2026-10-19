# ChatApp — README

A NetBeans-based Java console application that simulates a basic messaging platform. It includes **user registration**, **login**, **message creation with validation**, **message hashing**, and **JSON-based message storage**. The project is aligned with the **PROG5121** programming assignment (Parts 1 and 2).

---

## 📖 Overview

ChatApp lets a user:

1. **Register** with a first name, last name, username, password, and South African cell phone number — all validated against strict formatting rules.
2. **Log in** using the credentials they registered with (retries until successful).
3. **Send messages** by entering a recipient cell number and message text, then choosing to **Send**, **Store** (saved to `messages.json`), or **Disregard** each message.
4. **View recently sent messages** (currently a placeholder displaying "Coming Soon").

Every message is validated, assigned a unique 10-digit ID, and given a **message hash** used for identification.

---

## ✨ Features

### Part 1 — Registration & Login

- **Username validation** — must contain an underscore (`_`) and be **5 characters or fewer**.
- **Password complexity** — at least **8 characters**, with at least one **capital letter**, one **number**, and one **special character**.
- **Cell phone validation** — must start with `+27` followed by exactly 9 digits (e.g. `+27838968976`).
- **Login status messages** — friendly welcome message on success and a retry prompt on failure.

### Part 2 — Messaging

- **Recipient validation** — must start with `+` and contain **10–12 digits**.
- **Message length validation** — must be **non-empty and no more than 250 characters**.
- **Unique message ID** — a random 10-digit string, with the first digit between 1 and 9 and the remaining digits between 0 and 9.
- **Message hash** — format: `firstTwoIDchars:messageNumber:FIRSTWORDLASTWORD` (uppercase, letters only).

  Example: `00:0:HITONIGHT` for ID `0012345678`, message number `0`, and text "Hi Mike, … tonight?"

- **Message actions** — `Send` (marked `Sent`), `Store` (saved to `messages.json`), or `Disregard`.
- **Persistent storage** — stored messages are appended to `messages.json` using **Google Gson**.
- **Total processed counter** — counts messages that were either **sent** or **stored**. Disregarded messages are not counted.

---

## 🗂️ Project Structure

The project is a **NetBeans Java SE project** (`org.netbeans.modules.java.j2seproject`).

```text
ChatApp/
├── build.xml                          # Main Ant build script
├── manifest.mf                        # Manifest for the built JAR
├── README.TXT                         # NetBeans default build output notes
├── messages.json                      # Persistent store of stored messages
├── built-jar.properties               # NetBeans build cache (auto-generated)
├── nbproject/
│   ├── build-impl.xml                 # Auto-generated Ant implementation
│   ├── genfiles.properties            # Generated-file checksums
│   ├── project.properties             # Project-wide configuration
│   ├── project.xml                    # NetBeans project descriptor
│   ├── private/
│   │   ├── private.properties         # Machine-specific settings
│   │   └── private.xml                # IDE state and open-file information
│   └── configs/                       # Optional build configuration overrides
├── src/
│   └── chatapp/
│       ├── ChatApp.java               # Entry point — registration, login, main menu
│       ├── Login.java                 # Registration and login validation logic
│       ├── Message.java               # Message model, validation, ID and hash generation
│       └── MessageStorage.java        # JSON loading and saving using Gson
└── test/
    └── chatapp/
        └── ChatAppTest.java           # JUnit 4 test suite (Parts 1 and 2)
```

A `messages.json` file is created automatically in the working directory the first time a message is stored, provided the application is configured to create the file when it does not exist.

---

## ⚙️ Requirements

| Component | Version / Notes |
|---|---|
| **JDK** | Configured as `javac.source=26` and `javac.target=26` in `nbproject/project.properties`. Use a supported JDK version, such as 17 or 21, if Java 26 is unavailable, and update the project configuration accordingly. |
| **Apache Ant** | Version 1.8.0 or higher, as required by the build configuration. |
| **Google Gson** | `gson-2.10.1.jar`, referenced in `javac.classpath`. |
| **JSON library** | `json-20250517.jar`, also included in the configured classpath. |
| **JUnit** | JUnit 4, used by tests importing `org.junit.Test`. JUnit 5 is also referenced in the test classpath configuration. |
| **Hamcrest** | Required by the JUnit 4 command-line test setup if it is not already available through the JUnit dependency. |

### ⚠️ Classpath Notes

The `nbproject/project.properties` file contains the following references:

```properties
file.reference.gson-2.10.1.jar=C:\\Users\\DELL\\Desktop\\H` & Them\\gson-2.10.1.jar
file.reference.json-20250517.jar=C:\\Users\\DELL\\Desktop\\H` & Them\\json-20250517.jar
```

- These are **absolute paths tied to the original author's computer**. On another computer, add the JAR files through **Project Properties → Libraries → Add JAR/Folder**, or update the references to use a portable project-relative location, such as a `lib/` folder.
- The backtick and ampersand in the folder name may cause quoting issues in some command-line environments. A simpler folder name can help avoid these problems.
- Both **JUnit 4 and JUnit 5** appear in the test classpath configuration. Since the tests import `org.junit.Test`, JUnit 4 is used by those test classes. Remove unnecessary dependencies only after checking the project's test configuration.

---

## 🚀 How to Run

### Option A — NetBeans (Recommended)

1. Open **NetBeans IDE**.
2. Select **File → Open Project**.
3. Choose the `ChatApp` project folder.
4. Right-click the project and select **Properties → Libraries**.
5. Confirm that Gson and the required JSON library are on the classpath. Confirm that JUnit 4 is available for testing.
6. Right-click the project and select **Run**, or press **F6**.

### Option B — Apache Ant Command Line

Run these commands from the project root:

```bash
ant run
ant clean
ant jar
ant test
ant javadoc
ant clean jar
```

- `ant run` — compiles and runs the application.
- `ant clean` — removes generated build output.
- `ant jar` — builds the distributable JAR.
- `ant test` — compiles and runs the configured tests.
- `ant javadoc` — generates Javadoc documentation if the project is configured for it.
- `ant clean jar` — cleans and rebuilds the distributable JAR.

After a successful JAR build, the expected output locations are:

```text
dist/ChatApp.jar
dist/lib/
```

The `dist/lib/` folder contains dependencies copied by the build configuration, if configured.

Run the application using:

```bash
java -jar dist/ChatApp.jar
```

The JAR must have a valid main-class entry in its manifest. Any required external libraries must also be available through the manifest's `Class-Path` or another suitable runtime classpath.

### Option C — Plain `javac` / `java`

The following commands assume the Java source files are in the `chatapp` package directory and the JAR files are in the current directory.

**Compile:**

```bash
javac -cp "gson-2.10.1.jar:json-20250517.jar" -d out chatapp/*.java
```

**Run:**

```bash
java -cp "out:gson-2.10.1.jar:json-20250517.jar" chatapp.ChatApp
```

On Windows, replace the classpath separator `:` with `;`.

For a NetBeans project, the source files normally reside under `src/chatapp/`. If compiling directly from the project root, adjust the commands to use `src/chatapp/*.java` and include the required JAR files and output directory.

---

## 🧪 Running the Tests

The `ChatAppTest.java` file is documented as containing **22 unit tests** covering Parts 1 and 2. The actual number of tests should be confirmed by running the test suite.

### With NetBeans

- Right-click `ChatAppTest.java` and select **Test File** or **Run File**, depending on your NetBeans version and project configuration.
- Alternatively, right-click the project and select **Test** to run the configured project test suite.

### With Ant

```bash
ant test
```

Test reports are normally generated under the project's `build/test/results/` directory, depending on the Ant configuration.

### With the Command Line (JUnit 4)

The following commands assume the required source files and JAR files are in the stated locations. The example compiles the main source files; the test source must also be compiled before running JUnit.

```bash
javac -cp "gson-2.10.1.jar:json-20250517.jar:junit-4.13.2.jar:hamcrest-core-1.3.jar" -d out src/chatapp/*.java

javac -cp "out:gson-2.10.1.jar:json-20250517.jar:junit-4.13.2.jar:hamcrest-core-1.3.jar" -d out test/chatapp/*.java

java -cp "out:gson-2.10.1.jar:json-20250517.jar:junit-4.13.2.jar:hamcrest-core-1.3.jar" org.junit.runner.JUnitCore chatapp.ChatAppTest
```

On Windows, replace `:` with `;` in each classpath.

### Test Coverage

| Area | Tests |
|---|---:|
| Registration (valid/invalid username and invalid password) | 3 |
| Cell phone validation and messages | 3 |
| Login status messages (success/failure) | 2 |
| Field validation (username, password and phone) | 6 |
| Login success/failure | 2 |
| Recipient validation (valid/invalid) | 2 |
| Message length (valid/too long) | 2 |
| Message hash (official test data `00:0:HITONIGHT`) | 1 |
| Message ID length | 1 |
| **Total** | **22** |

The official hash test uses a forced ID through the overloaded constructor:

```java
Message msg = new Message(
        0,
        "+27718693002",
        "Hi Mike, can you join us for dinner tonight?",
        "0012345678"
);

assertEquals("00:0:HITONIGHT", msg.getMessageHash());
```

This example assumes that the constructor and getter have the signatures shown.

---

## 📌 Example Walkthrough

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

If **Store** is chosen, `messages.json` may contain an entry similar to:

```json
{
  "messageID": "0012345678",
  "messageNumber": 0,
  "recipient": "+27718693002",
  "messageText": "Hi Mike, can you join us for dinner tonight?",
  "messageHash": "00:0:HITONIGHT",
  "flag": "Stored"
}
```

The exact property names and JSON structure depend on the fields and serialization configuration in `Message.java` and `MessageStorage.java`.

---

## 📂 Sample `messages.json`

The project includes a sample `messages.json` showing two stored messages. Both demonstrate the stated hash rule:

```json
[
  {
    "messageID": "2900723710",
    "messageNumber": 0,
    "recipient": "+29693518072",
    "messageText": "Hi I was wondering if you are still available today?",
    "messageHash": "29:0:HITODAY",
    "flag": "Stored"
  },
  {
    "messageID": "9641663819",
    "messageNumber": 1,
    "recipient": "+27718693002",
    "messageText": "Ocean",
    "messageHash": "96:1:OCEANOCEAN",
    "flag": "Stored"
  }
]
```

### Hash Verification

- `2900723710` → first two ID characters `29`, message number `0`, first word `Hi`, last word `today` → `29:0:HITODAY`.
- `9641663819` → first two ID characters `96`, message number `1`, first word `Ocean`, last word `Ocean` → `96:1:OCEANOCEAN`.

**Note:** The recipient number `+29693518072` is included as sample data. It does not follow the South African `+27` registration rule, although it may satisfy the separate recipient validation rule.

---

## 🧾 Design Notes

- **`Message` constructors:** The class has a standard constructor that generates a random ID and an overloaded constructor that accepts a specific ID for deterministic testing.
- **`MessageStorage`:** Handles Gson operations. If `messages.json` does not exist, `loadMessages()` is intended to return an empty list so that the application can start with a clean storage state.
- **Message `flag`:** Tracks the message lifecycle: `Pending` → `Sent`, `Stored`, or `Disregard`.
- **`totalMessagesProcessed`:** In `ChatApp`, this counter is intended to count messages that were sent or stored. Disregarded messages are not counted.
- **NetBeans private configuration:** `nbproject/private/private.xml` records IDE-specific state, such as open files and bookmarks. It is normally generated locally and is not required to run the application.

---

## 📚 Attributions

- **Google. (2024).** *Gson User Guide.* Available at: https://github.com/google/gson/blob/main/UserGuide.md

  Used for JSON serialization and deserialization in `MessageStorage.java`.

- **JUnit 4:** Testing framework used in `ChatAppTest.java`.

- **NetBeans IDE:** The project is maintained as a NetBeans Java SE project. Files such as `nbproject/build-impl.xml` and `README.TXT` are generated or maintained by the IDE.

---

## 🔮 Possible Future Improvements

- Implement the **Show Recently Sent Messages** menu option, which currently displays `Coming Soon`.
- Add a message history viewer that reads previously stored messages from `messages.json`.
- Secure stored passwords using an appropriate password-hashing algorithm rather than storing them as plain text.
- Add a graphical user interface using JavaFX or Swing.
- Add more unit tests for `MessageStorage`, including save/load round-trip tests.
- Support international phone number formats beyond `+27`.
- Replace absolute `file.reference.*` paths in `nbproject/project.properties` with project-relative paths using a `lib/` folder.
- Align `javac.source` and `javac.target` with a supported JDK version installed in the development environment.
- Review dependencies and remove libraries that are not required by the actual implementation.

---

## 📄 License

This project was created for educational purposes as part of a programming assignment. It is free to use and modify for learning.
