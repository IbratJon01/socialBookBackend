package com.example.demo.Entety;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "follower")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "follower_user_id", referencedColumnName = "id")
    private Users followerUser;



    // O'zgaruvchilar, konstruktorlar, getter va setter metodlar, equals() va hashCode() metodlarini qo'shamiz


    public Follower(Long id, Users users, Users followerUser) {
        this.id = id;
        this.users = users;
        this.followerUser = followerUser;
    }

    public Follower() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Users getFollowerUser() {
        return followerUser;
    }

    public void setFollowerUser(Users followerUser) {
        this.followerUser = followerUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follower follower = (Follower) o;
        return Objects.equals(id, follower.id) && Objects.equals(users, follower.users) && Objects.equals(followerUser, follower.followerUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, users, followerUser);
    }



}
