package com.example.demo.Entety;

import jakarta.persistence.*;


import org.jetbrains.annotations.NotNull;

import java.util.Objects;


@Entity(name="Users")
public class Users {
    @Id
    @GeneratedValue
    private  Long id;

    @NotNull
    private  String userId;
    private  String userName;
    private  String name ;
    private String profileImage;
    private String bio;






    public Users() {
    // Bo'sh boshlang'ich konstruktor
}


    public Users(Long id, @NotNull String userId, String userName, String name, String profileImage, String bio) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.profileImage = profileImage;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(userId, users.userId) && Objects.equals(userName, users.userName) && Objects.equals(name, users.name) && Objects.equals(profileImage, users.profileImage) && Objects.equals(bio, users.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userName, name, profileImage, bio);
    }


}
