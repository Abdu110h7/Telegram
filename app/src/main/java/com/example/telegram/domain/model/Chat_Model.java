package com.example.telegram.domain.model;

public class Chat_Model {
    String chatKey;
    String massageKey;
    String message;
    String snderKey;
    String receiverKey;
    String timeStamp;
    boolean readStatus;

    public Chat_Model() {
    }

    public Chat_Model(String chatKey, String massageKey, String message, String snderKey, String receiverKey, String timeStamp, boolean readStatus) {
        this.chatKey = chatKey;
        this.massageKey = massageKey;
        this.message = message;
        this.snderKey = snderKey;
        this.receiverKey = receiverKey;
        this.timeStamp = timeStamp;
        this.readStatus = readStatus;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public String getChatKey() {
        return chatKey;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public String getMassageKey() {
        return massageKey;
    }

    public void setMassageKey(String massageKey) {
        this.massageKey = massageKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSnderKey() {
        return snderKey;
    }

    public void setSnderKey(String snderKey) {
        this.snderKey = snderKey;
    }

    public String getReceiverKey() {
        return receiverKey;
    }

    public void setReceiverKey(String receiverKey) {
        this.receiverKey = receiverKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
