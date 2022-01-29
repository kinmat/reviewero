package backend.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Review;
import backend.services.ReviewService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
    @RequestMapping("api/review")
    public List<Review> getReviewsByBook(@PathParam("book_id") Long book_id) {
    	return reviewService.getReviewsByBook(book_id);
    }
    
    @PostMapping("api/review/add") 
    public Review addReview(@RequestBody Review review) {
    	return reviewService.addReview(review);
    }
}
