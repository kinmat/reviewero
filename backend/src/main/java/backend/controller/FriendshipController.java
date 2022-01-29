package backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Friendship;
import backend.model.User;
import backend.services.FriendshipService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FriendshipController {
	@Autowired
	private FriendshipService friendService;

    @PostMapping("api/friend/add") 
    public Friendship addFriendRequest(@RequestBody Friendship fr) {
    	return friendService.addFriendRequest(fr);
    }
    
    @PostMapping("api/friend/accept") 
    public User acceptFriendRequest(@RequestBody Friendship fr) {
    	return friendService.setFriendshipAccepted(fr);
    }
    
}
