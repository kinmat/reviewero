package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String sender;
	private String reciever;
	private String content;
	
	public ChatMessage(Long id, String sender, String reciever, String content) {
		super();
		Id = id;
		this.sender = sender;
		this.reciever = reciever;
		this.content = content;
	}
	public ChatMessage(String sender, String reciever, String content) {
		super();
		this.sender = sender;
		this.reciever = reciever;
		this.content = content;
	}
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
}
