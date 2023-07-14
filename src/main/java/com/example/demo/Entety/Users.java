package com.example.demo.Entety;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Entity(name="Users")
public class Users {
    @Id
    @GeneratedValue
    private  int id;

    @NotNull
    private  String userId;
    private  String userName;
    private  String name ;
    private String profileImage;

    @JsonManagedReference

    @JsonIgnoreProperties("subscribers")
    @ManyToMany(mappedBy = "subscriptions")
    private Set<Users> subscribers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id")
    )
    private Set<Users> subscriptions = new HashSet<>();


    public Users() {
    // Bo'sh boshlang'ich konstruktor
}

    public Users(int id, @NotNull String userId, String userName, String name, String profileImage, Set<Users> subscribers, Set<Users> subscriptions) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.profileImage = profileImage;
        this.subscribers = subscribers;
        this.subscriptions = subscriptions;
    }

    public Set<Users> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<Users> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<Users> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Users> subscriptions) {
        this.subscriptions = subscriptions;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
