package com.example.demo.Controller;

import com.example.demo.Entety.Post;
import com.example.demo.Entety.Users;
import com.example.demo.Repository.PostRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.FollowingService;
import com.example.demo.Service.PostService;
import com.example.demo.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostController {


    private final PostRepo postRepo;
    private final UserRepo userRepo;

    @Autowired
    public PostController(PostRepo postRepo , UserRepo userRepo) {

        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @PostMapping("")
    private Post submitUserPost(@RequestBody Post post){

        return postService.submitPostToDataBase(post);
    }
    @GetMapping("")
    private ArrayList<Post> getAllPost(){
        return postService.retrievePostFromDB();
    }

    // bu bitta userga tegishli postlarni topib beradigan kod
    @GetMapping("/user/{userId}")
    public List<Post> getPostByUserId(@PathVariable String userId) {

        return postRepo.findByUserId(userId);
    }
    // bu bitta userga teg
    @DeleteMapping("/delete/{id}")
    public String deletePostById(@PathVariable int id) {
        postRepo.deleteById(id);
        return "Post with ID " + id + " has been deleted.";
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePostById(@PathVariable long postId, @RequestBody Post updatedPost) {
        Post existingPost = postRepo.findById(String.valueOf(postId));
        if (existingPost == null) {
            return ResponseEntity.notFound().build();
        }

        // Yangilangan malumotlarni o'zgartirish

        existingPost.setPostPath(updatedPost.getPostPath());
        existingPost.setLocalDate(updatedPost.getLocalDate());
        // Boshqa maydonlarni ham o'zgartirishingiz mumkin

        postRepo.save(existingPost);

        return ResponseEntity.ok("Post with ID " + postId + " has been updated.");
    }

    @GetMapping("/{userId}")
    public List<Post> getFollowingUsersPosts(@PathVariable String userId) {
        // Foydalanuvchini bazadan olib olamiz (bunda sizning vositalaringizni ishlatishingiz kerak)
        Users user = new Users();
        user.setUserId(userId);

        // Foydalanuvchining following bo'lgan foydalanuvchilarini olish
        List<Users> followingUsers =userService.getFollowingUsersUserId(userId);

        // Following bo'lgan foydalanuvchilarining postlarini olish
        List<Post> posts = postService.getPostsByUser(user);
        for (Users followingUser : followingUsers) {
            posts.addAll(postService.getPostsByUser(followingUser));
        }

        return posts;
    }

}
