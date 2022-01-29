package backend.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByTitle(String title);
	ArrayList<Book> findByTitleContains(String title);
	ArrayList<Book> findByAuthors_Id(Long Id);
	ArrayList<Book> findByAuthors_FullNameContains(String fullName);
}
