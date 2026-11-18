package chatapp;

import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String flag;

    // normal setup
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateID();
        this.messageHash = createHash();
        this.flag = "Pending";
    }

    // setup with forced id for the tests
    public Message(int messageNumber, String recipient, String messageText, String forcedID) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = forcedID;
        this.messageHash = createHash();
        this.flag = "Pending";
    }

    // making a 10 digit number, first can't be 0
    private String generateID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(10);
        sb.append(rand.nextInt(9) + 1);
        for (int i = 0; i < 9; i++) sb.append(rand.nextInt(10));
        return sb.toString();
    }

    // just checking the length is exactly 10
    public boolean checkMessageID() {
        return messageID != null && messageID.length() == 10;
    }

    // needs a + and 10 to 12 digits
    public boolean checkRecipientCell() {
        return recipient.matches("^\\+[0-9]{10,12}$");
    }

    // can't be over 250 and can't be empty
    public boolean checkMessageLength() {
        return messageText.length() <= 250 && !messageText.isEmpty();
    }

    // hash is first 2 id numbers, colon, msg number, colon, first and last word uppercase
    public String createHash() {
        if (messageID == null || messageID.length() < 2) return "";
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = "", lastWord = "";
        if (words.length > 0 && !words[0].isEmpty()) {
            firstWord = words[0].replaceAll("[^A-Za-z]", "").toUpperCase();
            lastWord  = words[words.length - 1].replaceAll("[^A-Za-z]", "").toUpperCase();
        }
        return firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // spitting out the details in order
    public String printMessage() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    // getters and setters
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public String getFlag() { return flag; }
    public void setFlag(String flag) { this.flag = flag; }
}