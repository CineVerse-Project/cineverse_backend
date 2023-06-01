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
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
 * SecurityConfiguration
 *
 * Version: 1.0
 *
 * Date: May 30, 2023
 *
 * Copyright
 *
 * Modification Log:
 *
 * DATE             AUTHOR          DESCRIPTION
 * ----------------------------------------- 
 * May 30,2023      HuuNQ
 *
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfiguration{


    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;


    /**
     * securityFilterChain
     * 
     * @Author: HuuNQ
     * @Day: 23 May 2023 | @Time: 08:23:36 TODO
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.cors().and().csrf().disable().
	sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and()
	.authenticationProvider(authenticationProvider)
	.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
	.authorizeHttpRequests()
	.antMatchers("/**").permitAll()
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
     * @param resourceLoader
     * @Day: 23 May 2023 | @Time: 08:21:26
     * @return FreeMarkerConfigurationFactoryBean
     */
    @Bean(name = "emailConfigBean")
    @Primary
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration(ResourceLoader resourceLoader) {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/templates/");
        return bean;
    }

    /**
     * persistentTokenRepository
     * 
     * @return PersistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }
}
