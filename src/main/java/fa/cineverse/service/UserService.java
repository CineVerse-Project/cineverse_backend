/**
 * Created At {14 May 2023
 * By HuuNQ
 */
package fa.cineverse.service;

import java.util.List;

import fa.cineverse.model.User;

/**
 * @author HuuNQ
 *
 * 14 May 2023
 * 
 */
public interface UserService {

	List<User> findUserByUsername(String username);

	void saveUser(User user);

	User findByUsername(String username);

	void createResetPassword(User user);

}
