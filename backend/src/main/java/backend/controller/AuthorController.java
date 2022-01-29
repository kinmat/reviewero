package backend.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Author;
import backend.services.AuthorService;

@RestController
@CrossOrigin
public class AuthorController {
	@Autowired
	AuthorService authorService;
	
    @RequestMapping("/api/authors")
    public Iterable<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
    
    @RequestMapping("/api/authors/{id}")
    public Author getAuthorByID(@PathVariable("id") Long id) {
    	return authorService.getAuthorByID(id);
    }
    
    @RequestMapping("/api/authors/search")
    public List<Author> searchAuthorByName(@PathParam("name") String name) {
    	return authorService.searchAuthorByName(name);

    }
    
}
