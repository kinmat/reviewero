package backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.model.BookListItem;
import backend.model.Review;
import backend.model.ReviewId;


public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
	Review findByBookIdAndUserId(Long bookId, Long userId);
	List<Review> findByBookId(Long BookId);
}
