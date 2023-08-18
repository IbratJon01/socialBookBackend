package com.example.demo.Service;

import com.example.demo.Entety.*;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
            message.setRead(false);
            messageRepository.save(message);

        }
    }


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

    public ChatUserMessages getUserChatMessages(String username) {
        Users user = userRepository.findByUserName(username);
        if (user != null) {
            List<Message> sentMessages = messageRepository.findBySenderOrderByTimestampAsc(user);
            List<Message> receivedMessages = messageRepository.findByReceiverOrderByTimestampAsc(user);
            List<Users> allUsers = new ArrayList<>();

            Map<String, Users> uniqueUsersMap = new HashMap<>(); // Unikal username lar uchun

            for (Message message : sentMessages) {
                Users receiver = message.getReceiver();
                if (!uniqueUsersMap.containsKey(receiver.getUserName())) {
                    uniqueUsersMap.put(receiver.getUserName(), receiver);
                }
            }

            for (Message message : receivedMessages) {
                Users sender = message.getSender();
                if (!uniqueUsersMap.containsKey(sender.getUserName())) {
                    uniqueUsersMap.put(sender.getUserName(), sender);
                }
            }

            for (Users chatUser : uniqueUsersMap.values()) {
                List<Message> userSentMessages = messageRepository.findBySenderAndReceiverOrderByTimestampDesc(user, chatUser);
                List<Message> userReceivedMessages = messageRepository.findBySenderAndReceiverOrderByTimestampDesc(chatUser, user);

                List<Message> unreadMessages = new ArrayList<>();
                Message lastReadMessage = null;

                for (Message message : userSentMessages) {
                    if (!message.isRead()) {
                        unreadMessages.add(message);
                    } else if (lastReadMessage == null || message.getTimestamp().after(lastReadMessage.getTimestamp())) {
                        lastReadMessage = message;
                    }
                }

                for (Message message : userReceivedMessages) {
                    if (!message.isRead()) {
                        unreadMessages.add(message);
                    } else if (lastReadMessage == null || message.getTimestamp().after(lastReadMessage.getTimestamp())) {
                        lastReadMessage = message;
                    }
                }

                ChatUserMessage chatUserMessage = new ChatUserMessage();
                chatUserMessage.setId(chatUser.getId());
                chatUserMessage.setUserId(chatUser.getUserId());
                chatUserMessage.setUserName(chatUser.getUserName());
                chatUserMessage.setName(chatUser.getName());
                chatUserMessage.setProfileImage(chatUser.getProfileImage());
                chatUserMessage.setBio(chatUser.getBio());

                if (!unreadMessages.isEmpty()) {
                    chatUserMessage.setNewMessages(unreadMessages);
                }

                chatUserMessage.setLastMessage(lastReadMessage);

                allUsers.add(chatUserMessage);
            }

            ChatUserMessages userMessages = new ChatUserMessages();
            userMessages.setAllUsers(allUsers);

            return userMessages;
        }
        return null;
    }

    public List<Message>  searchMessagesByContent(String user1,String user2,String content) {
        List<Message>  messages =messageRepository.findAll();

        List<Message> matchingUsers = new ArrayList<>();

        for (Message message : messages) {
            if (message.getContent().toLowerCase().contains(content.toLowerCase())) {
                matchingUsers.add(message);
            }
        }
        List<Message> userName = new ArrayList<>();
        for (Message message : matchingUsers) {
            if ((message.getSender().getUserName().equals(user1)&&(message.getReceiver().getUserName().equals(user2)))) {
                userName.add(message);
            }
        }

        return userName;

    }
//    public ChatUserMessagesIsRead getUserChatMessagesRead(String username){
//        Users user = userRepository.findByUserName(username);
//        if (user != null){
//            List<Message> sentMessages = messageRepository.findBySenderOrderByTimestampAsc(user);
//            List<Message> allTrueMessageOrFalse = new ArrayList<>();
//            List<Users> allUsers = new ArrayList<>();
//            for (Message message : sentMessages) {
//                if(message.isRead()){
//                    allTrueMessageOrFalse.add(message);
//                }
//                else {
//                    allTrueMessageOrFalse.add(message);
//                }
//            }
//            for (Message message : allTrueMessageOrFalse) {
//                for (Users users: allUsers){
//                    if (message.getReceiver().getUserName().equals(users.getUserName())){
//                        allTrueMessageOrFalse.add(message);
//                    }
//                }
//
//            }
//
//
//            Map<String, Users> uniqueUsersMap = new HashMap<>(); // Unikal username lar uchun
//            for (Message message : sentMessages) {
//                Users receiver = message.getReceiver();
//                if (!uniqueUsersMap.containsKey(receiver.getUserName())) {
//                    uniqueUsersMap.put(receiver.getUserName(), receiver);
//                }
//            }
//            List<Message> receivedMessages = messageRepository.findByReceiverOrderByTimestampAsc(user);
//            for (Message message : receivedMessages) {
//                Users sender = message.getSender();
//                if (!uniqueUsersMap.containsKey(sender.getUserName())) {
//                    uniqueUsersMap.put(sender.getUserName(), sender);
//                }
//            }
//            allUsers.addAll(uniqueUsersMap.values());
//            ChatUserMessagesIsRead userMessages = new ChatUserMessagesIsRead();
//            userMessages.setAllUsers(allUsers);
//            userMessages.setAllTrueMessageOrFalse(allTrueMessageOrFalse);
//            return userMessages;
//        }
//        return null;
//    }

    public List<Message> markMessagesAsReadBetweenUsers(String user1, String user2) {
        List<Message> messages = messageRepository.findUnreadMessagesBetweenUsers(user1, user2);
        for (Message message : messages) {
            message.setRead(true);
            messageRepository.save(message);
        }
        return messages;
    }
}
