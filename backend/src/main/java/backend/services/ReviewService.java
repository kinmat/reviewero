package backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.Review;
import backend.repo.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepo;
	
    public List<Review> getReviewsByBook(Long book_id) {
    	return reviewRepo.findByBookId(book_id);
    }
    
    public Review addReview(Review review) {
    	return reviewRepo.save(review);
    }
}
