package backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Author;
import backend.model.Book;
import backend.repo.AuthorRepository;

@RestController
@CrossOrigin
public class AuthorController {
	@Autowired
	AuthorRepository authorRepo;
	
    @RequestMapping("/api/authors")
    public Iterable<Author> getAllAuthors() {
        return authorRepo.findAll();
    }
    
    @RequestMapping("/api/authors/{id}")
    public Author getAuthorByID(@PathVariable("id") Long id) {
    	return authorRepo.findById(id).get();
    }
    
    @RequestMapping("/api/authors/search")
    public List<Author> searchAuthorByName(@PathParam("name") String name) {
    	return authorRepo.findByFullNameContains(name);

    }
    
}
