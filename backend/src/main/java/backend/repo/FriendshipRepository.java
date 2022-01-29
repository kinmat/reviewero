package backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.model.Friendship;
import backend.model.FriendshipId;

public interface FriendshipRepository extends JpaRepository<Friendship, FriendshipId> {

  //  @Query(nativeQuery = true)
    //List<Long> fetchFriends(@Param("id") Long id);
	Friendship findByRequesterIdAndRequesteeId(Long requesterId, Long requesteeId);
}