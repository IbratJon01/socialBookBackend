package com.example.demo.Controller;

import com.example.demo.Entety.Post;
import com.example.demo.Entety.Users;
import com.example.demo.Repository.PostRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.PostService;
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
        existingPost.setTimestamp(updatedPost.getTimestamp());
        // Boshqa maydonlarni ham o'zgartirishingiz mumkin

        postRepo.save(existingPost);

        return ResponseEntity.ok("Post with ID " + postId + " has been updated.");
    }


}
