package backend.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EnumRole name;

	public Role() {

	}
	
	public Role(String name) {
		EnumRole newName= EnumRole.ROLE_USER;
		switch(name) {
		case "ROLE_USER":
			newName=EnumRole.ROLE_USER;
			break;
		case "ADMIN":
			newName=EnumRole.ADMIN;
			break;
		case "AUTHOR":
			newName=EnumRole.AUTHOR;
			break;
		case "REVIEWER":
			newName=EnumRole.REVIEWER;
			break;
		}
		this.name=newName;
	}

	public Role(EnumRole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EnumRole getName() {
		return name;
	}

	public void setName(EnumRole name) {
		this.name = name;
	}
}
