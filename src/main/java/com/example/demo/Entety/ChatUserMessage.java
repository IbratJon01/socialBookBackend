package com.example.demo.Entety;

import java.util.List;

public class ChatUserMessage extends Users {
    private Long id;
    private String userId;
    private String userName;
    private String name;
    private String profileImage;
    private String bio;
    private Message lastMessage;
    private List<Message> newMessages;

    // Getter and setter methods for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public List<Message> getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(List<Message> newMessages) {
        this.newMessages = newMessages;
    }
}
