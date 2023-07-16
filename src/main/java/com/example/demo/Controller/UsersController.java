package com.example.demo.Controller;

import com.example.demo.Entety.Users;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;


    @GetMapping("/{username}/name")
    public Users getUserByUsername(@PathVariable String username) {
        return userService.getUserName(username);
    }

    @PostMapping("")
    private Users submitUser(@RequestBody Users users){
        return userService.submitMetaDataOfUser(users);

    }
    @GetMapping("/{userid}")
    private Users getUserDetails(@PathVariable("userid") String userId){
        return userService.displayUserMetaData(userId);

    }


    @GetMapping("/all")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/{userId}/follow/{followingUserId}")
    public void followUser(@PathVariable Long userId, @PathVariable Long followingUserId) {
        userService.followUser(userId, followingUserId);
    }

    @GetMapping("/{userId}/following")
    public List<Users> getFollowingUsers(@PathVariable Long userId) {
        return userService.getFollowingUsers(userId);
    }

    @GetMapping("/{userId}/followers")
    public List<Users> getFollowerUsers(@PathVariable Long userId) {
        return userService.getFollowerUsers(userId);
    }




}
