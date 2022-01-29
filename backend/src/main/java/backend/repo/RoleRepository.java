package backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.EnumRole;
import backend.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(EnumRole name);
}