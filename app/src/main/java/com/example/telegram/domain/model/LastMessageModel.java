package com.example.telegram.domain.model;

public class LastMessageModel {
    String messageKey;
    String timeStamp;
    String chatKey;
    String message;
    String name;
    String avatarUrl;
    UserData data;

    public LastMessageModel(String messageKey, String timeStamp, String chatKey, String message, String name, String avatarUrl) {
        this.messageKey = messageKey;
        this.timeStamp = timeStamp;
        this.chatKey = chatKey;
        this.message = message;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getChatKey() {
        return chatKey;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
