package backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import backend.model.ChatMessage;
import backend.repo.ChatMessageRepository;

@Controller
public class ChatWebSocketHandler {

	 private final SimpMessagingTemplate template;
	 @Autowired 
	 private ChatMessageRepository chatRepo;

	    @Autowired
	    ChatWebSocketHandler(SimpMessagingTemplate template){
	        this.template = template;
	    }

		/*
		 * @MessageMapping("/send/message") public void sendMessage(String message){
		 * this.template.convertAndSend("/message", message); }
		 */

	    @MessageMapping("/chat")
	    public void processMessage(@Payload ChatMessage chatMessage) {  
	        ChatMessage saved = chatRepo.save(chatMessage);
	        this.template.convertAndSendToUser(
	                chatMessage.getReciever(),"/queue/messages",
	                new ChatMessage(
	                        saved.getId(),
	                        saved.getSender(),
	                        saved.getReciever(),
	                        saved.getContent(),
	        				saved.getDateSent()));
	    }
	    
	}
