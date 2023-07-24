package com.example.demo.Service;

import com.example.demo.Entety.Following;
import com.example.demo.Entety.Post;
import com.example.demo.Entety.Status;
import com.example.demo.Entety.Users;
import com.example.demo.Repository.FollowingRepository;
import com.example.demo.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;

    private final FollowingRepository followingRepository;
    private final PostRepo postRepository;

    @Autowired
    public PostService(FollowingRepository followingRepository,  PostRepo postRepository) {
        this.followingRepository = followingRepository;
        this.postRepository = postRepository;
    }

    public List<Post> getPostsByUser(Users user) {
        return postRepository.findAllByUserId(user.getUserId());
    }
    public Post submitPostToDataBase(Post post){
        return postRepo.save(post);
    }
    public ArrayList<Post> retrievePostFromDB(){
        ArrayList<Post> postList=postRepo.findAll();

        for(int i=0;i<postList.size();i++) {
            Post postItem=postList.get(i);
            postItem.setUserName(userService.displayUserMetaData(postItem.getUserId()).getUserName());

        }
        Collections.sort(postList,(a,b)->b.getId()-a.getId());


        return postList;
    }
}
