/**
 * Created At {13 May 2023
 * By HuuNQ
 */
package fa.cineverse.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import fa.cineverse.service.impl.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author HuuNQ
 *
<<<<<<< HEAD
 *         13 May 2023
 * 
=======
 * 13 May 2023
 *
>>>>>>> ad95e652389d9dfe0a83f70a95b5d59e18e1ce58
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtCommon jwtCommon;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tokenWithBearer = request.getHeader("Authorization");
		if (tokenWithBearer == null || !tokenWithBearer.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = tokenWithBearer.substring(7);
		try {
			if (token != null && jwtCommon.validateJwtToken(token,request)) {
				String username = jwtCommon.getUsernameFromToken(token);
				UserDetails user = customUserDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
						null, user.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				filterChain.doFilter(request, response);
				return;
			}
		}catch(ExpiredJwtException e) {
			e.getCause();
		}
		
		filterChain.doFilter(request, response);
	}

	public String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
