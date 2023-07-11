package com.example.demo.Repository;

import com.example.demo.Entety.Comments;
import com.example.demo.Entety.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommentRepo extends CrudRepository<Comments, Integer> {

    Comments save(Comments comments);
    ArrayList<Comments> findAllByPostId(String postId);

}
