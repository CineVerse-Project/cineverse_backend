/**
 * Created At {12 May 2023
 * By HuuNQ
 */
package fa.cineverse.config;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fa.cineverse.common.JwtRequestFilter;

/**
 * @author HuuNQ
 *
 * 12 May 2023
 * 
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//CORS DE CAC HOST KHAC TRUY CAP DUOC
		//CSRF DUNG DE SU DUNG POST REQUEST TRANH BI TAN CONG
		http.cors().and().csrf().disable();
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.authorizeHttpRequests(
				(auth)->
				{
						try {
							auth
							//REQUEST DANH CHO TAT CA
//							.antMatchers(HttpMethod.GET,"/api/v1/user/account").permitAll()
//							.antMatchers(
//									HttpMethod.POST,
//									"/api/v1/admin/login"
//									,"/api/v1/user/login"
//									,"/api/v1/customer/sign-up")
							.anyRequest()
							.permitAll()
							//REQUEST DANH CHO ADMIN HOAC USER
//							.antMatchers(HttpMethod.GET,"/api/v1/customer/**").hasAnyRole("USER","ADMIN")
//							.antMatchers(HttpMethod.PATCH,"/api/v1/user/**").hasAnyRole("USER","ADMIN")
							//REQUEST PHAI LA ADMIN
//							.antMatchers(HttpMethod.GET,"/api/v1/admin/admin").hasRole("ADMIN")
							//TAT CA CAC REQUEST KHAC DEU PHAI DUOC CHAP NHAN
//							.anyRequest().authenticated()
							.and()
							//KHONG TAO SESSION
							.sessionManagement()
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
							.and()
							//XU LY CAC REQUEST KHONG DUOC CHAP NHAN
							.exceptionHandling()
							.authenticationEntryPoint(new AuthenticationEntryPoint() {
								@Override
								public void commence(HttpServletRequest request, HttpServletResponse response,
										AuthenticationException authException) throws IOException, ServletException {
									// TODO Auto-generated method stub
									response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error: unauthorized");
								}
							})
							.and()
							.httpBasic();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				});
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
