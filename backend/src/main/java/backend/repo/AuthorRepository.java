package backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	Optional<Author> findByFullName(String fullName);
	List<Author> findByFullNameContains(String fullName);
}