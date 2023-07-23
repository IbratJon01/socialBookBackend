package com.example.demo.Controller;

import com.example.demo.Service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class FollowerController {

    @Autowired
    private FollowerService followerService;


    //Unga obuna bo'lgan userni o'chiradi
    @DeleteMapping("/{followerUserId}/following/{followingUserId}")
    public void unfollowUser(@PathVariable Long followerUserId, @PathVariable Long followingUserId) {
        followerService.unfollowUser(followerUserId, followingUserId);
    }
}
