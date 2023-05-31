package fa.cineverse.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fa.cineverse.model.User;
import fa.cineverse.repository.UserRepository;

/**
* ApplicationConfig
*
* Version: 1.0
*
* Date: May 30, 2023
*
* Copyright
*
* Modification Log:
*
* DATE          AUTHOR          DESCRIPTION 
* -----------------------------------------
* May 30, 2023  HuuNQ               
*
*/
@Configuration
public class ApplicationConfig {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * userDetailsService
     * @Author: HuuNQ
     * @Day: 23 May 2023 | @Time: 08:23:36 TODO
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
    	return new UserDetailsService() {
	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Không tìm thấy người dùng");
		}
		List<GrantedAuthority> authority = new ArrayList<>();
		user.getUserRole().forEach(
				role -> authority.add(new SimpleGrantedAuthority(role.getRole().getRoleName()))
				);
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),user.getPassword(),authority);
	    }
	};
	
    }
    /**
     * passwordEncoder
     * @Author: HuuNQ
     * @Day: 23 May 2023 | @Time: 08:23:36 TODO
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
    
    /**
     * authenticationProvider
     * @Author: HuuNQ
     * @Day: 23 May 2023 | @Time: 08:23:36 TODO
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	authProvider.setUserDetailsService(userDetailsService());
	authProvider.setPasswordEncoder(passwordEncoder());
	return authProvider;
	
    }
    
    /**
     * authenticationManager
     * @Author: HuuNQ
     * @Day: 23 May 2023 | @Time: 08:23:36 TODO
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	return config.getAuthenticationManager();
    }

}
