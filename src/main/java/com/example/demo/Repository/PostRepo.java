package com.example.demo.Repository;

import com.example.demo.Entety.Post;
import com.example.demo.Entety.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post , Integer> {
    Post save(Post post);
    ArrayList<Post> findAll();

    // bitta userga tegishli postlarni olib beradi

    List<Post> findByUserId(String userId);
    Post findById(String postId);
}
