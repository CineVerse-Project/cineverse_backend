/**
 * Created At {14 May 2023
 * By HuuNQ
 */
package fa.cineverse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fa.cineverse.model.User;
import fa.cineverse.repository.UserRepository;
import fa.cineverse.service.UserService;

/**
 * @author HuuNQ
 *
 * 14 May 2023
 * 
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;


	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		String passwordEncode = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordEncode);
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		if(user != null) {
			return user;
		}
		return null;
	}

	@Override
	public void createResetPassword(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String passwordEncoded = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordEncoded);
		userRepository.save(user);
	}

}