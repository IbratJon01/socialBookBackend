package com.example.demo.Repository;

import com.example.demo.Entety.Message;
import com.example.demo.Entety.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
   List<Message> findBySenderAndReceiverOrderByTimestampAsc(Users sender, Users receiver);


    List<Message> findBySenderInAndReceiverInOrderByTimestampAsc(Collection<Users> sender, Collection<Users> receiver);

    List<Message> findByReceiverOrderByTimestampAsc(Users user);

    List<Message> findBySenderOrderByTimestampAsc(Users user);
}