package backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.BookListItem;
import backend.model.BookState;
import backend.repo.BookListItemRepository;
import backend.repo.StateRepository;

@Service
public class BookListItemService {
	@Autowired 
	private BookListItemRepository itemRepo;
	
	@Autowired 
	private StateRepository stateRepo;
	
    public List<BookState> getBookAllStates() {
    	return stateRepo.findAll();
    }
    
    public BookListItem addBookListItem(BookListItem item) {
    	return itemRepo.save(item);
    }
    
    public BookListItem changeBookListItemState(BookListItem item) {
    	BookListItem i=itemRepo.findByBookIdAndUserId(item.getBook().getId(), item.getUser().getId());
    	BookState newState=item.getState();
    	i.setState(newState);
    	return itemRepo.saveAndFlush(i);
    }
    
    public void deleteBookListItem(Long book_id, Long user_id) {
    	BookListItem item=itemRepo.findByBookIdAndUserId(book_id, user_id);
    	itemRepo.delete(item);
    }

}
