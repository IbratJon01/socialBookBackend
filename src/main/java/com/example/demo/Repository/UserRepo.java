package com.example.demo.Repository;

import com.example.demo.Entety.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepo extends JpaRepository<Users , Integer> {

    Users save(Users user);
    Users findByUserId(String userId);
// Users findById(Long id);

    Users deleteByUserId(String userId);

    Optional<Users> findById(Long id);

    Users findByUserName(String userName);

}
