package backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(	name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String synopsis;
	private Long publisher_id;
	private Integer pages;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "author_books", 
				joinColumns = @JoinColumn(name = "book_id"), 
				inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors = new HashSet<>();
	
	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Book(String title, String synopsis, Long publisher_id, Integer pages, Set<Author> authors) {
		super();
		this.title = title;
		this.synopsis = synopsis;
		this.publisher_id = publisher_id;
		this.pages = pages;
		this.authors = authors;
	}

	public Book() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Long getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(Long publisher_id) {
		this.publisher_id = publisher_id;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}
		

}
