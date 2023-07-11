package com.example.demo.Repository;

import com.example.demo.Entety.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepo extends CrudRepository<Users , Integer> {

    Users save(Users user);
    Users findByUserId(String userId);


    Users findByUserName(String userName);

}
