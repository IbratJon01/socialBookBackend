package com.example.demo.Service;

import com.example.demo.Entety.ChatUserMessages;
import com.example.demo.Entety.Message;
import com.example.demo.Entety.Users;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private MessageRepository messageRepository;

    public void sendChatMessage(String senderUsername, String receiverUsername, String content) {
        Users sender = userRepository.findByUserName(senderUsername);
        Users receiver = userRepository.findByUserName(receiverUsername);

        if (sender != null && receiver != null) {
            Message message = new Message();
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setContent(content);
            message.setTimestamp(new Date());
            messageRepository.save(message);
        }
    }

//    public List<Message> getChatMessages(String senderUsername, String receiverUsername) {
//        Users sender = userRepository.findByUserName(senderUsername);
//        Users receiver = userRepository.findByUserName(receiverUsername);
//
//        if (sender != null && receiver != null) {
//            return messageRepository.findBySenderAndReceiverOrderByTimestampAsc(sender, receiver);
//        }
//
//        return Collections.emptyList();
//    }

    public List<Message> getChatMessagesBetweenUsers(String user1Username, String user2Username) {
        Users user1 = userRepository.findByUserName(user1Username);
        Users user2 = userRepository.findByUserName(user2Username);

        if (user1 != null && user2 != null) {
            List<Message> messages1 = messageRepository.findBySenderAndReceiverOrderByTimestampAsc(user1, user2);
            List<Message> messages2 = messageRepository.findBySenderAndReceiverOrderByTimestampAsc(user2, user1);

            List<Message> allMessages = new ArrayList<>(messages1);
            allMessages.addAll(messages2);

            allMessages.sort(Comparator.comparing(Message::getTimestamp));

            return allMessages;
        }

        return Collections.emptyList();
    }

    // Yozishgan chatlar ruyxati

//    public ChatUserMessages getUserChatMessages(String username) {
//        Users user = userRepository.findByUserName(username);
//        if (user != null) {
//            List<Message> sentMessages = messageRepository.findBySenderOrderByTimestampAsc(user);
//            List<Users> sentToUsers = sentMessages.stream()
//                    .map(Message::getReceiver)
//                    .collect(Collectors.toList());
//
//            List<Message> receivedMessages = messageRepository.findByReceiverOrderByTimestampAsc(user);
//            List<Users> receivedFromUsers = receivedMessages.stream()
//                    .map(Message::getSender)
//                    .collect(Collectors.toList());
//
//            ChatUserMessages userMessages = new ChatUserMessages();
//            userMessages.setSentToUsers(sentToUsers);
//            userMessages.setReceivedFromUsers(receivedFromUsers);
//
//            return userMessages;
//        }
//        return null;
//    }


//    public ChatUserMessages getChatUserMessages(String username) {
//        List<Message> sentMessages = messageRepository.findBySenderOrderByTimestampAsc(userRepository.findByUserName(username));
//        List<Users> sentToUsers = sentMessages.stream()
//                .map(Message::getReceiver)
//                .collect(Collectors.toList());
//
//        List<Message> receivedMessages = messageRepository.findByReceiverOrderByTimestampAsc(userRepository.findByUserName(username));
//        List<Users> receivedFromUsers = receivedMessages.stream()
//                .map(Message::getSender)
//                .collect(Collectors.toList());
//
//        ChatUserMessages userMessages = new ChatUserMessages();
//        userMessages.setSentToUsers(sentToUsers);
//        userMessages.setReceivedFromUsers(receivedFromUsers);
//
//        return userMessages;
//    }
public ChatUserMessages getUserChatMessages(String username) {
    Users user = userRepository.findByUserName(username);
    if (user != null) {
        List<Message> sentMessages = messageRepository.findBySenderOrderByTimestampAsc(user);
        List<Users> allUsers = new ArrayList<>();

        Map<String, Users> uniqueUsersMap = new HashMap<>(); // Unikal username lar uchun

        for (Message message : sentMessages) {
            Users receiver = message.getReceiver();
            if (!uniqueUsersMap.containsKey(receiver.getUserName())) {
                uniqueUsersMap.put(receiver.getUserName(), receiver);
            }
        }

        List<Message> receivedMessages = messageRepository.findByReceiverOrderByTimestampAsc(user);

        for (Message message : receivedMessages) {
            Users sender = message.getSender();
            if (!uniqueUsersMap.containsKey(sender.getUserName())) {
                uniqueUsersMap.put(sender.getUserName(), sender);
            }
        }

        allUsers.addAll(uniqueUsersMap.values());

        ChatUserMessages userMessages = new ChatUserMessages();
        userMessages.setAllUsers(allUsers);

        return userMessages;
    }
    return null;
}
}
