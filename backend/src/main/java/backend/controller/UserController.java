package backend.controller;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.model.User;
import backend.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	@Autowired
	UserService userService;
	
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        return userService.user(request);
    }
    
    @RequestMapping("/api/user/{id}")
    public User getUserByID(@PathVariable("id") Long id) {
    	return userService.getUserByID(id);
    }
    
    @RequestMapping("/api/user/search")
    public List<User> searchBookByTitle(@PathParam("username") String username) {
    	return userService.searchBookByTitle(username);
    }
    
  
}