package com.example.demo.Controller;

import com.example.demo.Entety.ChatMessageRequest;
import com.example.demo.Entety.ChatUserMessages;
import com.example.demo.Entety.Message;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/send")
    public ResponseEntity<String> sendChatMessage(@RequestBody ChatMessageRequest request) {
        chatService.sendChatMessage(request.getSender(), request.getReceiver(), request.getContent());
        return ResponseEntity.ok("Message sent successfully.");
    }



    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getChatMessages(
            @RequestParam String user1, @RequestParam String user2) {
        List<Message> messages = chatService.getChatMessagesBetweenUsers(user1, user2);
        return ResponseEntity.ok(messages);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Message>> searchMessagesByContent(
            @RequestParam String user1, @RequestParam String user2 , @RequestParam String content) {
        List<Message> messages = chatService.searchMessagesByContent(user1, user2,content);

        return ResponseEntity.ok(messages);
    }

    @GetMapping("/mark-as-read")
    public ResponseEntity<List<Message>> markMessagesAsRead(
            @RequestParam String user1, @RequestParam String user2) {
        List<Message> messages = chatService.markMessagesAsReadBetweenUsers(user1, user2);
        return ResponseEntity.ok(messages);
    }
    // xabar yozgan va yozilgan barcha userlarni ruyxati
    @GetMapping("/allmessages/{username}")
    public ResponseEntity<ChatUserMessages> getUserChatMessages(@PathVariable String username) {
        ChatUserMessages userMessages = chatService.getUserChatMessages(username);
        if (userMessages != null) {
            return ResponseEntity.ok(userMessages);
        } else {
            return ResponseEntity.notFound().build();
        }
    }//getUserChatMessagesRead

//    @GetMapping("/new/{username}")
//    public ResponseEntity<ChatUserMessagesIsRead> getUserChatMessagesNew(@PathVariable String username) {
//        ChatUserMessagesIsRead userMessages = chatService.getUserChatMessagesRead(username);
//        if (userMessages != null) {
//           return ResponseEntity.ok(userMessages);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }

    @DeleteMapping("/delete/{id}")
    public String deletePostById(@PathVariable Long id) {
        messageRepository.deleteById(id);
        return "Post with ID " + id + " has been deleted.";
    }
}
