package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fa.cineverse.model.UserRole;

/**
 * The Interface UserRoleRepository.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
