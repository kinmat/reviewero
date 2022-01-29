package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.model.ChatMessage;
import backend.model.Friendship;
import backend.services.ChatService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ChatMessageController {
	@Autowired
	private ChatService chatService;
	
    @PostMapping("api/last-messages")
    public List<ChatMessage> getLastMessagesByFriendship(@RequestBody Friendship fr) {
    	return chatService.getMessages(fr);
    }
}
