package com.example.demo.Controller;

import com.example.demo.Entety.Users;
import com.example.demo.Repository.SubscriptionRepository;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @GetMapping("/{username}/name")
    public Users getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
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


    @GetMapping("/{id}/subscribers")
    public ResponseEntity<Set<Users>> getSubscribers(@PathVariable("id") Long id) {
        Set<Users> subscribers = userService.findSubscribedUsers(id);
        return ResponseEntity.ok(subscribers);
    }



    @PostMapping("/{subscriberId}/subscribe/{subscriptionId}")
    public ResponseEntity<String> subscribeUser(
            @PathVariable("subscriberId") Long subscriberId,
            @PathVariable("subscriptionId") Long subscriptionId) {
        userService.subscribeUser(subscriberId, subscriptionId);
        return ResponseEntity.ok("User subscribed successfully.");
    }



}
