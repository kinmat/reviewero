package backend.controller;

import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Book;
import backend.model.Friendship;
import backend.model.FriendshipId;
import backend.model.User;
import backend.repo.FriendshipRepository;
import backend.repo.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FriendshipRepository friendRepo;
	
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
          .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
          .decode(authToken)).split(":")[0];
    }
    
    @RequestMapping("/api/user/{id}")
    public User getUserByID(@PathVariable("id") Long id) {
    	return userRepo.findById(id).get();
    }
    
    @RequestMapping("/api/user/search")
    public List<User> searchBookByTitle(@PathParam("username") String username) {
    	return userRepo.findByUsernameContains(username);
    }
    
 /*   @RequestMapping("/api/friends/{id}")
    public List<Long> getFriends(@PathParam("id") Long id) {
    	return friendRepo.fetchFriends(id);
    }
    */
    
    @PostMapping("api/friend/add") 
    public Friendship addFriendRequest(@RequestBody Friendship fr) {
    	return friendRepo.save(fr);
    }
    
    @PostMapping("api/friend/accept") 
    public User acceptFriendRequest(@RequestBody Friendship fr) {
    	Friendship f=friendRepo.findByRequesterIdAndRequesteeId(fr.getRequester().getId(), fr.getRequestee().getId());
    	f.setAccepted(true);
    	friendRepo.saveAndFlush(f);
    	return f.getRequestee();
    }
    
    
}