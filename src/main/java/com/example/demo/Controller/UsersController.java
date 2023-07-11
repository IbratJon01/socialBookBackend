package com.example.demo.Controller;

import com.example.demo.Entety.Users;
import com.example.demo.Repository.SubscriptionRepository;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.UserSerivse;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {

    UserSerivse userSerivse;

    @Autowired
    public void UserController(UserSerivse userSerivse) {
        this.userSerivse = userSerivse;
    }
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

    @PostMapping("/{subscriberId}/subscribe/{subscribedToId}")
    public ResponseEntity<String> subscribeUser(
            @PathVariable Long subscriberId,
            @PathVariable Long subscribedToId
    ) {
        userSerivse.subscribe(subscriberId, subscribedToId);
        return new ResponseEntity<>("Subscribed successfully", HttpStatus.OK);
    }


}

//44min
