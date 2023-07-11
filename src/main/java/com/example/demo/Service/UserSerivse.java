package com.example.demo.Service;

import com.example.demo.Entety.Users;
import org.apache.catalina.User;

public interface UserSerivse {

    User createUser(User user);

    Users createUser(Users user);

    User getUserById(Long id);
    void subscribe(Long subscriberId, Long subscribedToId);
    // Qo'shimcha metodlar
}
