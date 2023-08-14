package com.example.demo.Service;

import com.example.demo.Entety.Follower;
import com.example.demo.Entety.Following;
import com.example.demo.Entety.Message;
import com.example.demo.Entety.Users;

import com.example.demo.Repository.FollowerRepository;
import com.example.demo.Repository.FollowingRepository;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    private MessageRepository messageRepository;
    private final UserRepo userRepository;
    private final FollowingRepository followingRepository;
    private final FollowerRepository followerRepository;

    public UserService(UserRepo userRepository, FollowingRepository followingRepository, FollowerRepository followerRepository) {
        this.userRepository = userRepository;
        this.followingRepository = followingRepository;
        this.followerRepository = followerRepository;
    }


    public Users createUsersALl(Users users) {
        return userRepository.save(users);

    }

    public void followUser(Long userId, Long followingUserId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Users followingUser = userRepository.findById(followingUserId).orElseThrow(() -> new IllegalArgumentException("Following user not found"));

        Following following = new Following();
        following.setUsers(user);
        following.setFollowingUser(followingUser);
        followingRepository.save(following);

        Follower follower = new Follower();
        follower.setUsers(followingUser);
        follower.setFollowerUser(user);
        followerRepository.save(follower);
    }

    public List<Users> getFollowingUsers(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Following> followingList = followingRepository.findByUsers(user);
        List<Users> followingUsers = new ArrayList<>();
        for (Following following : followingList) {
            followingUsers.add(following.getFollowingUser());
        }
        return followingUsers;
    }

    public List<Users> getFollowingUsersUserId(String userId) {
        Users user = userRepository.findByUserId(userId);
        List<Following> followingList = followingRepository.findByUsers(user);
        List<Users> followingUsers = new ArrayList<>();
        for (Following following : followingList) {
            followingUsers.add(following.getFollowingUser());
        }
        return followingUsers;
    }

    public List<Users> getFollowerUsers(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Follower> followerList = followerRepository.findByUsers(user);
        List<Users> followerUsers = new ArrayList<>();
        for (Follower follower : followerList) {
            followerUsers.add(follower.getFollowerUser());
        }
        return followerUsers;
    }

    //__________________________________________________________________________________________


    public Users findByUsername(String userName) {
        return userRepo.findByUserName(userName);
    }


    public Users submitMetaDataOfUser(Users user) {
        return userRepo.save(user);
    }

    public Users displayUserMetaData(String userid) {
        return userRepo.findByUserId(userid);
    }

    public Optional<Users> findId(Long id) {
        return userRepo.findById(id);
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }
//    public Users getUserName(String userName) {
//        return userRepo.findByUserName(userName); //users name select
//    }


    public List<Users> searchUsersByUsername(String userName) {
        List<Users> users = userRepository.findAll();
        List<Users> matchingUsers = new ArrayList<>();

        for (Users user : users) {
            if (user.getUserName().toLowerCase().contains(userName.toLowerCase())) {
                matchingUsers.add(user);
            }
        }

        return matchingUsers;
    }
}