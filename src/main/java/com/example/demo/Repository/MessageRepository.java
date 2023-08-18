package com.example.demo.Repository;

import com.example.demo.Entety.Message;
import com.example.demo.Entety.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
   List<Message> findBySenderAndReceiverOrderByTimestampAsc(Users sender, Users receiver);

    List<Message> findByContent(String content);

    @Query("SELECT m FROM Message m WHERE (m.sender.userName = :user1 AND m.receiver.userName = :user2) AND m.isRead = false")
    List<Message> findUnreadMessagesBetweenUsers(String user1, String user2);
    List<Message> findBySenderInAndReceiverInOrderByTimestampAsc(Collection<Users> sender, Collection<Users> receiver);

    List<Message> findByReceiverOrderByTimestampAsc(Users user);

    List<Message> findBySenderOrderByTimestampAsc(Users user);

 List<Message> findBySenderAndReceiverOrderByTimestampDesc(Users user, Users chatUser);
}