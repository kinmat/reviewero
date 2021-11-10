package backend.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Author;
import backend.model.Book;
import backend.repo.BookRepository;

@RestController
@CrossOrigin
public class BookController {
	@Autowired
	BookRepository bookRepo;
	
    @RequestMapping("/api/books")
    public Iterable<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    
    @RequestMapping("/api/books/{id}")
    public Book getBookByID(@PathVariable("id") Long id) {
    	return bookRepo.findById(id).get();
    }
    
    @RequestMapping("/api/books/author/{id}")
    public List<Book> getBookByAuthorID(@PathVariable("id") Long id) {
    	return bookRepo.findByAuthors_Id(id);
    }
    
    @RequestMapping("/api/books/search")
    public List<Book> searchBookByTitle(@PathParam("title") String title) {
    	return bookRepo.findByTitleContains(title);
    }
    
}
