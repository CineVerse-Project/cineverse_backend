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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * @author HuuNQ
 *
 *         13 May 2023
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtCommon jwtCommon;

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {
		String authenHeader = request.getHeader("Authorization");
		if(authenHeader == null || !authenHeader.startsWith("Bearer ")) {
		    filterChain.doFilter(request, response);
		    return;
		}
		String jwt = getTokenFromRequest(request);
		String username = jwtCommon.getUsernameFromToken(jwt);
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		UserDetails user = customUserDetailsService.loadUserByUsername(username);
			if (user != null && jwtCommon.validateJwtToken(jwt)) {
			    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				    user, null, user.getAuthorities());
			    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
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
