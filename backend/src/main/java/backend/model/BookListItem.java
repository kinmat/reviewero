package backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "user_books")
public class BookListItem {
	@EmbeddedId
	BookListItemId bookListId= new BookListItemId();
	
	@ManyToOne
	@MapsId("userId")
	private User user;
	
	@ManyToOne
	@MapsId("bookId")
	private Book book;
	
	
    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name="state_id")
	private BookState state;
	
	private Date dateAdded;
	
	public BookListItem() {
		super();
	}
	
	public BookListItem(BookListItemId bookListId, User user, Book book, BookState state, Date dateAdded) {
		super();
		this.bookListId = bookListId;
		this.user = user;
		this.book = book;
		this.state = state;
		this.dateAdded = dateAdded;
	}
	

	public BookListItem(User user, Book book, BookState state, Date dateAdded) {
		super();
		this.user = user;
		this.book = book;
		this.state = state;
		this.dateAdded = dateAdded;
	}

	public BookListItemId getBookListId() {
		return bookListId;
	}

	public void setBookListId(BookListItemId bookListId) {
		this.bookListId = bookListId;
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

	public BookState getState() {
		return state;
	}
	public void setState(BookState state) {
		this.state = state;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	
}
