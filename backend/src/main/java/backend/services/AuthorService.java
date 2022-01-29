package backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.Author;
import backend.repo.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	AuthorRepository authorRepo;

    public Iterable<Author> getAllAuthors() {
        return authorRepo.findAll();
    }
    
    public Author getAuthorByID(Long id) {
    	return authorRepo.findById(id).get();
    }
    
    public List<Author> searchAuthorByName(String name) {
    	return authorRepo.findByFullNameContains(name);

    }
}
