package com.example.demo.Controller;

import com.example.demo.Entety.Users;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController<updatedUsers> {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUsersById(@PathVariable String userId , @RequestBody Users updatedUser ) {
        Users existingUsers = userRepo.findByUserId(String.valueOf(userId));
       if (existingUsers == null) {
           return ResponseEntity.notFound().build();
       }
            existingUsers.setProfileImage(updatedUser.getProfileImage());
            existingUsers.setUserName(updatedUser.getUserName());
            existingUsers.setName(updatedUser.getName());
            existingUsers.setBio(updatedUser.getBio());

        userRepo.save(existingUsers);
        return ResponseEntity.ok("Post with ID " + userId + " has been updated.");

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


    @GetMapping("/search")
    public ResponseEntity<List<Users>> searchUsersByUsername(@RequestParam String userName) {
        List<Users> users = userService.searchUsersByUsername(userName);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUsersByuserId(@PathVariable String userId) {
        userRepo.deleteByUserId(userId);
        return "Post with ID " + userId + " has been deleted.";
    }



}
