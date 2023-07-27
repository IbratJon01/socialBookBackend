package com.example.demo.Entety;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity(name="Post")
public class Post {
    @Id
    @GeneratedValue
    private int Id;

    private String postId;
    private String userId;
    private String userName;
    private String postPath;
    private LocalDate localDate;
    private int likeCount;
    private String information ;
    private String location;
    private String file ;

    public Post(){
        super();

    }

    public Post(int id, String postId, String userId, String userName, String postPath, LocalDate localDate, int likeCount, String information, String location, String file) {
        Id = id;
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.postPath = postPath;
        this.localDate = localDate;
        this.likeCount = likeCount;
        this.information = information;
        this.location = location;
        this.file = file;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
