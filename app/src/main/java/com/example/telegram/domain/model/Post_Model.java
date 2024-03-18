package com.example.telegram.domain.model;

public class Post_Model {

    String name;
    int avatar;
    String time;
    String lastMessage;

    public Post_Model(String name, int avatar, String time, String lastMessage) {
        this.name = name;
        this.avatar = avatar;
        this.time = time;
        this.lastMessage = lastMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}