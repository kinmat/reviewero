package backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.Book;
import backend.model.BookState;

@Repository
public interface StateRepository extends JpaRepository<BookState, Long> {
	
}

