package backend.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Book;
import backend.services.BookService;

@RestController
@CrossOrigin
public class BookController {
	@Autowired
	BookService bookService;
	
    @RequestMapping("/api/books")
    public Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    
    @RequestMapping("/api/books/{id}")
    public Book getBookByID(@PathVariable("id") Long id) {
    	return bookService.getBookByID(id);
    }
    
    @RequestMapping("/api/books/author/{id}")
    public List<Book> getBookByAuthorID(@PathVariable("id") Long id) {
    	return bookService.getBookByAuthorID(id);
    }
    
    @RequestMapping(path = "/api/books/search", params = { "title" })
    public List<Book> searchBookByTitle(@PathParam("title") String title) {
    	return bookService.searchBookByTitle(title);
    }
    
    @RequestMapping(path = "/api/books/search", params = { "author" })
    public List<Book> searchBookByAuthor(@PathParam("author") String author) {
    	return bookService.searchBookByAuthor(author);
    }
    
}
