package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_states")
public class BookState {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EnumState name;

	public BookState() {

	}
	
	public BookState(String name) {
		EnumState newName= EnumState.to_read;
		switch(name) {
		case "to_read":
			newName=EnumState.to_read;
			break;
		case "read":
			newName=EnumState.read;
			break;
		}
		this.name=newName;
	}

	public BookState(EnumState name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EnumState getName() {
		return name;
	}

	public void setName(EnumState name) {
		this.name = name;
	}
	
	
}
