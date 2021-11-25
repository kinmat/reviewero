package backend.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ReviewId implements Serializable{
	private static final long serialVersionUID = 100;
	private Long userId;
	private Long bookId;
	
	
	public ReviewId() {
	}
	
	

    public ReviewId(Long userId, Long bookId) {
		super();
		this.userId = userId;
		this.bookId = bookId;
	}



	@Override
    public int hashCode() {
        return Objects.hash(userId, bookId);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReviewId other = (ReviewId) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (bookId == null) {
            if (other.bookId != null)
                return false;
        } else if (!bookId.equals(other.bookId))
            return false;
        return true;
    }   
}
