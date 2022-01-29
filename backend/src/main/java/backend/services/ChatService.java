package backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.ChatMessage;
import backend.model.Friendship;
import backend.repo.ChatMessageRepository;

@Service
public class ChatService {
	@Autowired 
	private ChatMessageRepository chatRepo;

	public List<ChatMessage> getMessages(Friendship fr) {
    	String userOneId=fr.getRequestee().getId().toString();
    	String userTwoId = fr.getRequester().getId().toString();	
    	List<ChatMessage> messages= chatRepo.findByRecieverAndSender(userOneId, userTwoId);
    	messages.addAll(chatRepo.findByRecieverAndSender(userTwoId, userOneId));
    	return messages;
	}
	
}
