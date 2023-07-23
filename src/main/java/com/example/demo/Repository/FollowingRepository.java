package com.example.demo.Repository;


import com.example.demo.Entety.Following;
import com.example.demo.Entety.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {
    List<Following> findByUsers(Users users);

    // Foydalanuvchi (A)ning idsi va foydalanuvchi (B)ning idsi bilan "Following" entitilarni qidirish uchun metod
    Following findByUsersIdAndFollowingUserId(Long usersId, Long followingUserId);
}