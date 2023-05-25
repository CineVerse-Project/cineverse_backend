
package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fa.cineverse.model.Role;

/**
 * @author HuuNQ
 *
 * 11 May 2023
 * 
 */
@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleName(String roleName);

}
