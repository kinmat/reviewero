package backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.model.Friendship;
import backend.model.FriendshipId;

public interface FriendshipRepository extends JpaRepository<Friendship, FriendshipId> {

  //  @Query(nativeQuery = true)
    //List<Long> fetchFriends(@Param("id") Long id);
	Friendship findByRequesterIdAndRequesteeId(Long requesterId, Long requesteeId);
}