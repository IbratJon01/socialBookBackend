package com.example.demo.Service;

import com.example.demo.Entety.Follower;
import com.example.demo.Entety.Following;
import com.example.demo.Repository.FollowerRepository;
import com.example.demo.Repository.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowingService {

    @Autowired
    private FollowingRepository followingRepository;

    @Autowired
    private FollowerRepository followerRepository;

    public void unfollowUser(Long followingUserId, Long followerUserId) {
        // Following jadvalidan foydalanuvchi (followingUserId) va follower foydalanuvchi (followerUserId) uchun
        // bog'lanishni topamiz
        Following followingEntry = followingRepository.findByUsersIdAndFollowingUserId(followingUserId, followerUserId);
        Follower followerEntry = followerRepository.findByFollowerUserIdAndUsersId(followingUserId, followerUserId);

        // Agar bog'lanish mavjud bo'lsa, uni o'chiramiz
        if ((followingEntry != null)&&(followerEntry!=null)) {
            followingRepository.delete(followingEntry);
            followerRepository.delete(followerEntry);
        }
    }
}
