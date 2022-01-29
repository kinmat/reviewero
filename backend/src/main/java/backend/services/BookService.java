package backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.Book;
import backend.repo.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepo;
	
    public Iterable<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    
    public Book getBookByID(Long id) {
    	return bookRepo.findById(id).get();
    }

    public List<Book> getBookByAuthorID(Long id) {
    	return bookRepo.findByAuthors_Id(id);
    }
    
    public List<Book> searchBookByTitle(String title) {
    	return bookRepo.findByTitleContains(title);
    }
    
    public List<Book> searchBookByAuthor(String author) {
    	return bookRepo.findByAuthors_FullNameContains(author);
    }
}



