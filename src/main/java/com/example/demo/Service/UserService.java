package com.example.demo.Service;

import com.example.demo.Entety.Users;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public Users submitMetaDataOfUser(Users user){
        return userRepo.save(user);
    }

    public Users displayUserMetaData(String userid){
        return userRepo.findByUserId(userid);
    }

    public Users getUserByUsername(String userName) {
        return userRepo.findByUserName(userName); //users name select
    }


}
