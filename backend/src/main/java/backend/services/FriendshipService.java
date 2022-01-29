package backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.Friendship;
import backend.model.User;
import backend.repo.FriendshipRepository;

@Service
public class FriendshipService {
	
	@Autowired
	FriendshipRepository friendRepo;
	
	public User setFriendshipAccepted(Friendship fr) {
		Long erId = fr.getRequester().getId();
		Long eeId = fr.getRequestee().getId();
    	Friendship f=friendRepo.findByRequesterIdAndRequesteeId(erId, eeId);
    	f.setAccepted(true);
    	friendRepo.saveAndFlush(f);
    	return f.getRequestee();
	}
	
	public Friendship addFriendRequest(Friendship fr) {
		
		return friendRepo.save(fr);
		
	}

}
