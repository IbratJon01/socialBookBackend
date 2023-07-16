package com.example.demo.Entety;



import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "following")
public class Following {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "following_user_id")
    private Users followingUser;

    // O'zgaruvchilar, konstruktorlar, getter va setter metodlar, equals() va hashCode() metodlarini qo'shamiz


    public Following(Long id, Users users, Users followingUser) {
        this.id = id;
        this.users = users;
        this.followingUser = followingUser;
    }

    public Following() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Following following = (Following) o;
        return Objects.equals(id, following.id) && Objects.equals(users, following.users) && Objects.equals(followingUser, following.followingUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, users, followingUser);
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

    public Users getFollowingUser() {
        return followingUser;
    }

    public void setFollowingUser(Users followingUser) {
        this.followingUser = followingUser;
    }
}
