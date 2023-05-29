/**
 * Created At {12 May 2023
 * By HuuNQ
 */
package fa.cineverse.config;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

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
		//CORS để các host đều được truy cập
		//CSRF được dùng để tránh các trường hợp bị tấn công csrf
		http.cors().and().csrf().disable();

		http.authorizeHttpRequests(
				(auth)->
				{
						try {
							auth
							//Request không bị yêu cầu xác thực danh tính
//							.antMatchers(
//									HttpMethod.POST,
//									"/api/v1/admin/login"
//									,"/api/v1/login"
//									,"/api/v1/sign-up")
							.anyRequest()
							.permitAll()
							//Request dành cho role user
//							.antMatchers(HttpMethod.GET,"/api/v1/user/**").hasRole("USER")
							//Request dành cho role admin hoặc user
//							.antMatchers(HttpMethod.PATCH,"/api/v1/user/user-update").hasAnyRole("USER","ADMIN")
							//Request dành cho role admin
//							.antMatchers(HttpMethod.GET,"/api/v1/admin/admin").hasRole("ADMIN")
							//Tất cả các request khác đều phải được xác thực danh tính
//							.anyRequest().authenticated()
							.and()
							//Không tạo sessionJID
							.sessionManagement()
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
							.and()
							//Xử lý các request bị từ chối truy cập
							.exceptionHandling()
							.authenticationEntryPoint(new AuthenticationEntryPoint() {
								@Override
								public void commence(HttpServletRequest request, HttpServletResponse response,
										AuthenticationException authException) throws IOException, ServletException {
									// TODO Auto-generated method stub
									response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error: unauthorized");
								}
							});
//							.and()
//							.httpBasic();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				});
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	/**
	 * @Author: HuuNQ
	 * @Day: 23 May 2023 | @Time: 08:23:36
	 * TODO
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
	@Bean(name="emailConfigBean")
	@Primary
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration(ResourceLoader resourceLoader) {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/templates/");
        return bean;
    }
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	    mailSender.setHost("smtp.gmail.com");
//	    mailSender.setPort(587);
//
//	    mailSender.setUsername("cineverse.service@gmail.com");
//	    mailSender.setPassword("java2301s");
//
//	    Properties props = mailSender.getJavaMailProperties();
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.smtp.auth", "true");
//	    props.put("mail.smtp.starttls.enable", "true");
//	    props.put("mail.debug", "true");
//
//	    return mailSender;
//	}

}
