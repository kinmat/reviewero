package backend.services;

import java.security.Principal;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend.model.User;
import backend.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
		
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
          .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
          .decode(authToken)).split(":")[0];
    }
    
    public User getUserByID(Long id) {
    	return userRepo.findById(id).get();
    }
    
    public List<User> searchBookByTitle(String username) {
    	return userRepo.findByUsernameContains(username);
    }
    

}
