package com.example.demo.Service;

import com.example.demo.Entety.Users;

import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public Users findByUsername(String userName) {
        return userRepo.findByUserName(userName);
    }


    public Set<Users> findSubscribedUsers(Long id) {
        Users user = userRepo.findById(Math.toIntExact(id)).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return new HashSet<>(user.getSubscribers());
    }

    public void subscribeUser(Long subscriberId, Long subscriptionId) {
        Users subscriber = userRepo.findById(Math.toIntExact(subscriberId)).orElse(null);
        Users subscription = userRepo.findById(Math.toIntExact(subscriptionId)).orElse(null);

        if (subscriber == null || subscription == null) {
            throw new IllegalArgumentException("User not found");
        }

        subscriber.getSubscriptions().add(subscription);
        userRepo.save(subscriber);
    }
    public Users submitMetaDataOfUser(Users user){
        return userRepo.save(user);
    }

    public Users displayUserMetaData(String userid){
        return userRepo.findByUserId(userid);
    }
    public Users findId(Long id){
        return userRepo.findById(id);
    }

    public List<Users> getAllUsers() {
        return (List<Users>) userRepo.findAll();
    }
    public Users getUserByUsername(String userName) {
        return userRepo.findByUserName(userName); //users name select
    }



}
