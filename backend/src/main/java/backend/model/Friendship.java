package backend.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Friendship {
	
	@EmbeddedId
	FriendshipId friendshipId= new FriendshipId();
	
	@ManyToOne
	@MapsId("requesterId")
	@JsonIgnoreProperties({"requestee", "requester"})
	private User requester;
	
	@ManyToOne
	@MapsId("requesteeId")
	@JsonIgnoreProperties({"requestee", "requester"})
	private User requestee;
	
	private Boolean accepted;
	public Friendship() {
	}
	
	
	public FriendshipId getFriendshipId() {
		return friendshipId;
	}


	public void setFriendshipId(FriendshipId friendshipId) {
		this.friendshipId = friendshipId;
	}


	public User getRequester() {
		return requester;
	}


	public void setRequester(User requester) {
		this.requester = requester;
	}


	public User getRequestee() {
		return requestee;
	}


	public void setRequestee(User requestee) {
		this.requestee = requestee;
	}


	public Boolean getAccepted() {
		return accepted;
	}


	public Friendship(FriendshipId friendshipId, User requester, User requestee, Boolean accepted) {
		super();
		this.friendshipId = friendshipId;
		this.requester = requester;
		this.requestee = requestee;
		this.accepted = accepted;
	}


	public Boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	
	
}
