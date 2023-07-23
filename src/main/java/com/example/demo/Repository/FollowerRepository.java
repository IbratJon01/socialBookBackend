package com.example.demo.Repository;

import com.example.demo.Entety.Follower;
import com.example.demo.Entety.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    List<Follower> findByUsers(Users users);

    Follower findByFollowerUserIdAndUsersId(Long followerUserId, Long usersId);}

