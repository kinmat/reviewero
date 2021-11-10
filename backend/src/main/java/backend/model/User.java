package backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String username;

	private String email;

	@JsonIgnore
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	//@OneToMany( mappedBy="requester", cascade=CascadeType.ALL, orphanRemoval = true)
	//   private Set<Friendship> friends = new HashSet<>();
	
	@OneToMany(mappedBy = "requester")
	@JsonIgnoreProperties("requester")
	private Set<Friendship> requester=new HashSet<>();
	
	@OneToMany(mappedBy = "requestee")
	@JsonIgnoreProperties("requestee")
	private Set<Friendship> requestee=new HashSet<>();
	
	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	

	public Set<Friendship> getRequester() {
		return requester;
	}

	public void setRequester(Set<Friendship> requester) {
		this.requester = requester;
	}

	public Set<Friendship> getRequestee() {
		return requestee;
	}

	public void setRequestee(Set<Friendship> requestee) {
		this.requestee = requestee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}