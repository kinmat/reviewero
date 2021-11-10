package backend.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class FriendshipId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 100;
	private Long requesterId;
	private Long requesteeId;
	
	
	public FriendshipId() {
	}


	public FriendshipId(Long requesterId, Long requesteeId) {
		this.requesterId = requesterId;
		this.requesteeId = requesteeId;
	}
	
    @Override
    public int hashCode() {
        return Objects.hash(requesterId, requesteeId);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FriendshipId other = (FriendshipId) obj;
        if (requesterId == null) {
            if (other.requesterId != null)
                return false;
        } else if (!requesterId.equals(other.requesterId))
            return false;
        if (requesteeId == null) {
            if (other.requesteeId != null)
                return false;
        } else if (!requesteeId.equals(other.requesteeId))
            return false;
        return true;
    }
    
    
	
	

}
