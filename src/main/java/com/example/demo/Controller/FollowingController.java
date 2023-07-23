package com.example.demo.Controller;

import com.example.demo.Service.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class FollowingController {

    @Autowired
    private FollowingService followingService;


    // Obuna bo'lgan userni uchiradi
    @DeleteMapping("/{followingUserId}/followers/{followerUserId}")
    public void unfollowUser(@PathVariable Long followingUserId, @PathVariable Long followerUserId) {
        followingService.unfollowUser(followingUserId, followerUserId);
    }
}
