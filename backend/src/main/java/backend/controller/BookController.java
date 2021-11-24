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
import backend.model.BookListItem;
import backend.model.BookState;
import backend.repo.BookListItemRepository;
import backend.repo.BookRepository;
import backend.repo.StateRepository;

@RestController
@CrossOrigin
public class BookController {
	@Autowired
	BookRepository bookRepo;
	
	@Autowired 
	private BookListItemRepository itemRepo;
	
	@Autowired 
	private StateRepository stateRepo;
	
	
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
    
    @RequestMapping("/api/list/states")
    public List<BookState> getBookAllStates() {
    	return stateRepo.findAll();
    }
    
    @PostMapping("api/list/add-book") 
    public BookListItem addBookListItem(@RequestBody BookListItem item) {
    	
    	return itemRepo.save(item);
    }
    
    @PostMapping("api/list/change-book-state") 
    public BookListItem changeBookListItemState(@RequestBody BookListItem item) {
    	BookListItem i=itemRepo.findByBookIdAndUserId(item.getBook().getId(), item.getUser().getId());
    	i.setState(item.getState());
    	itemRepo.saveAndFlush(i);
    	return i;
    }
    
}
