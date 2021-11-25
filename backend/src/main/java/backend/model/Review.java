package backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reviews")
public class Review {
	@EmbeddedId
	ReviewId reviewId= new ReviewId();
	
	@ManyToOne
	@MapsId("userId")
	@JsonIgnoreProperties({"requestee", "requester"})
	private User user;
	
	@ManyToOne
	@MapsId("bookId")
	private Book book;
	
	private int rating;
	private String text;	
	private Date dateAdded;
	
	public Review(User user, Book book, int rating, String text, Date dateAdded) {
		super();
		this.user = user;
		this.book = book;
		this.rating = rating;
		this.text = text;
		this.dateAdded = dateAdded;
	}
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewId getReviewId() {
		return reviewId;
	}
	public void setReviewId(ReviewId reviewId) {
		this.reviewId = reviewId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	
	
}
