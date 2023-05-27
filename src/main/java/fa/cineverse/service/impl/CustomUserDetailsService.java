/**
 * Created At {13 May 2023
 * By HuuNQ
 */
package fa.cineverse.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fa.cineverse.dto.CustomUserDetails;
import fa.cineverse.model.User;
import fa.cineverse.repository.UserRepository;

/**
 * @author HuuNQ
 *
 * 13 May 2023
 * 
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	public UserRepository userRepository;
	
	/**
	 * @author HuuNQ
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Không tìm thấy người dùng");
		}
		List<GrantedAuthority> authority = new ArrayList<>();
		user.getUserRole().forEach(
				role -> authority.add(new SimpleGrantedAuthority(role.getRole().getRoleName()))
				);
		return new CustomUserDetails(user,authority);
	}

}
