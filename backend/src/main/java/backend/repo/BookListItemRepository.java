package backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.BookListItem;
import backend.model.BookListItemId;

@Repository
public interface BookListItemRepository extends JpaRepository<BookListItem, BookListItemId> {
	BookListItem findByBookIdAndUserId(Long bookId, Long userId);
}