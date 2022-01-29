package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.model.BookListItem;
import backend.model.BookState;
import backend.services.BookListItemService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BookListItemController {
	@Autowired
	BookListItemService listService;
	
    @RequestMapping("/api/list/states")
    public List<BookState> getBookAllStates() {
    	return listService.getBookAllStates();
    }
    
    @PostMapping("api/list/add-book") 
    public BookListItem addBookListItem(@RequestBody BookListItem item) {
    	return listService.addBookListItem(item);
    }
    
    @PostMapping("api/list/change-book-state") 
    public BookListItem changeBookListItemState(@RequestBody BookListItem item) {
    	return listService.changeBookListItemState(item);
    }
    
    @DeleteMapping("api/list/delete")
    public void deleteBookListItem(@RequestParam Long book_id, @RequestParam Long user_id) {
    	listService.deleteBookListItem(book_id, user_id);
    }

}
