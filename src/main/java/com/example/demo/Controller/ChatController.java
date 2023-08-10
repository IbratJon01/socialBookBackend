package com.example.demo.Controller;

import com.example.demo.Entety.ChatMessageRequest;
import com.example.demo.Entety.ChatUserMessages;
import com.example.demo.Entety.Message;
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

    @PostMapping("/send")
    public ResponseEntity<String> sendChatMessage(@RequestBody ChatMessageRequest request) {
        chatService.sendChatMessage(request.getSender(), request.getReceiver(), request.getContent());
        return ResponseEntity.ok("Message sent successfully.");
    }

//    @GetMapping("/messages")
//    public ResponseEntity<List<Message>> getChatMessages(@RequestParam String sender, @RequestParam String receiver) {
//        List<Message> messages = chatService.getChatMessages(sender, receiver);
//        return ResponseEntity.ok(messages);
//    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getChatMessages(
            @RequestParam String user1, @RequestParam String user2) {
        List<Message> messages = chatService.getChatMessagesBetweenUsers(user1, user2);
        return ResponseEntity.ok(messages);
    }
//    @GetMapping("/messages/{username}")
//    public ResponseEntity<ChatUserMessages> getUserChatMessages(@PathVariable String username) {
//        ChatUserMessages userMessages = chatService.getUserChatMessages(username);
//        return ResponseEntity.ok(userMessages);
//    }


    @GetMapping("/allmessages/{username}")
    public ResponseEntity<ChatUserMessages> getUserChatMessages(@PathVariable String username) {
        ChatUserMessages userMessages = chatService.getUserChatMessages(username);
        if (userMessages != null) {
            return ResponseEntity.ok(userMessages);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
