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
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
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
public class SecurityConfiguration{

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
    @Autowired
    private AuthenticationProvider authenticationProvider;

    /**
     * @Author: HuuNQ
     * @Day: 23 May 2023 | @Time: 08:23:36 TODO
     * @Return:
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.cors().and().csrf().disable().
	sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and()
	.authenticationProvider(authenticationProvider)
	.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
	.authorizeHttpRequests()
	.antMatchers(HttpMethod.POST,"/api/v1/sign-in/admin",
				"/api/v1/sign-in","/api/v1/sign-up",
				"/api/v1/reset-password","/api/v1/forgot-password")
			.permitAll()
			.antMatchers("/api/v1/user/**").hasRole("USER")
			.antMatchers("/api/v1/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
        			@Override
        			public void commence(HttpServletRequest request, HttpServletResponse response,
        					AuthenticationException authException) throws IOException, ServletException {
        				// TODO Auto-generated method stub
        			    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
        			}
        		});
			
	return http.build();
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

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
	return new InMemoryTokenRepositoryImpl();
    }
}
