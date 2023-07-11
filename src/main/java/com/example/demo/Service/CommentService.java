package com.example.demo.Service;

import com.example.demo.Entety.Comments;
import com.example.demo.Entety.Post;
import com.example.demo.Repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserService userService;

    public Comments submitCommentToDB(Comments comments){
        return commentRepo.save(comments);
    }
    public ArrayList<Comments> getAllCommentsForDB(String commentId){
        ArrayList<Comments> commentList= commentRepo.findAllByPostId(commentId);

        for(int i=0;i<commentList.size();i++) {
            Comments commentItem=commentList.get(i);
            commentItem.setUserName(userService.displayUserMetaData(commentItem.getUserId()).getUserName());

        }
        return commentList;
    }
}
