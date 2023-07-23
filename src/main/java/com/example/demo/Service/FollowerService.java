package com.example.demo.Service;

import com.example.demo.Entety.Follower;
import com.example.demo.Entety.Following;
import com.example.demo.Repository.FollowerRepository;
import com.example.demo.Repository.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerService {

    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private FollowingRepository followingRepository;

    public void unfollowUser(Long followerUserId, Long followingUserId) {
        // Follower jadvalidan foydalanuvchi (followerUserId) va following foydalanuvchi (followingUserId) uchun
        // bog'lanishni topamiz
        Follower followerEntry = followerRepository.findByFollowerUserIdAndUsersId(followerUserId, followingUserId);
        Following followingEntry = followingRepository.findByUsersIdAndFollowingUserId(followerUserId, followingUserId);
        // Agar bog'lanish mavjud bo'lsa, uni o'chiramiz
        if ((followerEntry != null)&&(followingEntry!=null)) {
            followerRepository.delete(followerEntry);
            followingRepository.delete(followingEntry);
        }
    }
}
