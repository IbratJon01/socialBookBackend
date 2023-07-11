package com.example.demo.Repository;

import com.example.demo.Entety.Status;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface StatusRepo extends CrudRepository<Status ,Integer> {
    Status save(Status status);
    ArrayList<Status> findAll();
    @Query("UPDATE Status s SET s.userName = (SELECT u.userName FROM Users u WHERE s.userId = u.userId)")
    void updateUsernameFromUserTable();

    List<Status> findByUserId(String userId);
}
