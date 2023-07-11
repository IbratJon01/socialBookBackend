package com.example.demo.Controller;

import com.example.demo.Entety.Comments;
import com.example.demo.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("")
    private Comments submitComment(@RequestBody Comments comments){
        return commentService.submitCommentToDB(comments);
    }
    @GetMapping("/{postId}")
    private ArrayList<Comments> getCommentsForPost(@PathVariable("postId") String postId){
        return commentService.getAllCommentsForDB(postId);



    }
}
