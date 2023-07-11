package com.example.demo.Entety;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity(name="Post")
public class Post {
    @Id
    @GeneratedValue
    private int Id;

    private String postId;
    private String userId;
    private String userName;
    private String postPath;
    private Timestamp timestamp;
    private int likeCount;

    public Post(){
        super();

    }

    public Post(int id, String postId, String userId, String userName, String postPath, Timestamp timestamp, int likeCount) {
        Id = id;
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.postPath = postPath;
        this.timestamp = timestamp;
        this.likeCount = likeCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostPath() {
        return postPath;
    }

    public void setPostPath(String postPath) {
        this.postPath = postPath;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
