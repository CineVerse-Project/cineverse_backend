/**
 * Created At {12 May 2023
 * By HuuNQ
 */
package fa.cineverse.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import fa.cineverse.common.JwtRequestFilter;

/**
 * @author HuuNQ
 *
 *         12 May 2023
 * 
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	/**
	 * @Author: HuuNQ
	 * @Day: 23 May 2023 | @Time: 08:23:36 TODO
	 * @Return:
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * @Author: HuuNQ
	 * @Day: 23 May 2023 | @Time: 08:23:36 TODO
	 * @Return: AuthenticationManager
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	/**
	 * @Author: HuuNQ
	 * @Day: 23 May 2023 | @Time: 08:23:36 TODO
	 * @Return:
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resources/**");
	}

	/**
	 * @Author: HuuNQ
	 * @Day: 23 May 2023 | @Time: 08:23:36 TODO
	 * @Return:
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// CORS để các host đều được truy cập
		// CSRF được dùng để tránh các trường hợp bị tấn công csrf
		http.csrf().disable();
		http.cors();
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.authorizeRequests().antMatchers("/api/v1/sign-in/admin", "/api/v1/sign-in", "/api/v1/sign-up",
				"/api/v1/reset-password", "/api/v1/forgot-password").permitAll();
		// Request dành cho role user
		http.authorizeRequests().antMatchers("/api/v1/user/**").hasAnyAuthority("ROLE_USER");
		// Request dành cho role admin
		http.authorizeRequests().antMatchers("/api/v1/admin/**").hasAuthority("ROLE_ADMIN");
		http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				// TODO Auto-generated method stub
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: unauthorized");
			}
		});
	}

	/**
	 * @Author: HuuNQ
	 * @Day: 23 May 2023 | @Time: 08:23:36 TODO
	 * @Return: PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * @Author: HuuNQ
	 * @Day: 23 May 2023 | @Time: 08:21:26
	 * @Return: FreeMarkerConfigurationFactoryBean
	 */
	@Bean(name = "emailConfigBean")
	@Primary
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration(ResourceLoader resourceLoader) {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("classpath:/templates/");
		return bean;
	}

}
