/**
 * Created At {13 May 2023
 * By HuuNQ
 */
package fa.cineverse.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HuuNQ
 *
 * 13 May 2023
 *
 */
@Slf4j
@Component
public class JwtCommon {
	private final String SECRECT_KEY = "cineverse";
    private final Integer EXPIRED_TOKEN_MS = 600_000;
    public String generateToken(Authentication authentication){
    	UserDetails user =  (UserDetails) authentication.getPrincipal();
        return Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+EXPIRED_TOKEN_MS))
                .signWith(SignatureAlgorithm.HS512, SECRECT_KEY)
                .compact();
    }

    public String getUsernameFromToken(String token){
    	Claims claims = Jwts.parser()
    			.setSigningKey(SECRECT_KEY)
    			.parseClaimsJws(token)
    			.getBody();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(SECRECT_KEY).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e){
        	System.out.println("Invalid JWT Token:"+e.getMessage());
        } catch (ExpiredJwtException e){
            System.out.println("JWT token is expired:"+e.getMessage());
        } catch (UnsupportedJwtException e){
            System.out.println("JWT token is unsupported:"+e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("JWT claims string is empty::"+e.getMessage());
        }
        return false;
    }
}
