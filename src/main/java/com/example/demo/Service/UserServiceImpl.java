package com.example.demo.Service;

import com.example.demo.Entety.Users;
import com.example.demo.Repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserSerivse {
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public Users createUser(Users user) {
        return userRepo.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public void subscribe(Long subscriberId, Long subscribedToId) {
        User subscriber = userRepo.findById(subscriberId).orElse(null);
        User subscribedTo = userRepo.findById(subscribedToId).orElse(null);

        if (subscriber != null && subscribedTo != null) {
            ((Users) subscriber).getSubscriptions().add(subscribedTo);
            userRepo.save((Users) subscriber);
        }
    }
    // Qo'shimcha metodlarni implementatsiyasini yozing
}
