package backend.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Author;
import backend.model.Book;
import backend.model.BookListItem;
import backend.model.BookState;
import backend.model.Review;
import backend.repo.BookListItemRepository;
import backend.repo.BookRepository;
import backend.repo.ReviewRepository;
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
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	
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
    	System.out.print(item.getState().getId().toString());
    	BookState newState=item.getState();
    	i.setState(newState);
    	return itemRepo.saveAndFlush(i);
    }
    
    @DeleteMapping("api/list/delete")
    public void deleteBookListItem(@RequestParam Long book_id, @RequestParam Long user_id) {
    	BookListItem item=itemRepo.findByBookIdAndUserId(book_id, user_id);
    	itemRepo.delete(item);
    }
    
    @RequestMapping("api/review")
    public List<Review> getReviewsByBook(@PathParam("book_id") Long book_id) {
    	return reviewRepo.findByBookId(book_id);
    }
    
    @PostMapping("api/review/add") 
    public Review addReview(@RequestBody Review review) {
    	return reviewRepo.save(review);
    }
    
}
