package backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.Author;
import backend.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByTitle(String title);
	List<Book> findByTitleContains(String title);
	List<Book> findByAuthors_Id(Long Id);
}
