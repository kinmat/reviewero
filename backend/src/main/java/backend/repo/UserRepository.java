package backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User getByUsername(String username);
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	List<User> findByUsernameContains(String username);
}
