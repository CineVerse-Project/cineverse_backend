/**
 * Created At {11 May 2023
 * By HuuNQ
 */
package fa.cineverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fa.cineverse.model.User;

/**
 * @author HuuNQ
 *
 * 11 May 2023
 * 
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String>{
	
	User findByUsername(String username);
	List<User> findAllUserByUsername(String username);
}
