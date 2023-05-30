/**
 * Created At {13 May 2023
 * By HuuNQ
 */
package fa.cineverse.common;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
* JwtCommon
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
@Slf4j
@Component
public class JwtCommon {
    private static final Logger LOG = LoggerFactory.getLogger(JwtCommon.class);
	private final String SECRECT_KEY = "cineverse";
    private final Integer EXPIRED_TOKEN_MS = 600_000;
    
    /**
     * generateToken
     * @param authentication
     * @return String
     */
    public String generateToken(Authentication authentication){
    	UserDetails user =  (UserDetails) authentication.getPrincipal();
        return Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+EXPIRED_TOKEN_MS))
                .signWith(SignatureAlgorithm.HS512, SECRECT_KEY)
                .compact();
    }

    /**
     * getUsernameFromToken
     * @param token
     * @return String
     */
    public String getUsernameFromToken(String token){
    	Claims claims = Jwts.parser()
    			.setSigningKey(SECRECT_KEY)
    			.parseClaimsJws(token)
    			.getBody();
        return claims.getSubject();
    }

    /**
     * validateJwtToken
     * @param authToken
     * @return boolean
     */
    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(SECRECT_KEY).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e){
        	LOG.error("Invalid JWT Token:"+e.getMessage());
        } catch (ExpiredJwtException e){
            LOG.error("JWT token is expired:"+e.getMessage());
        } catch (UnsupportedJwtException e){
            LOG.error("JWT token is unsupported:"+e.getMessage());
        } catch (IllegalArgumentException e){
            LOG.error("JWT claims string is empty::"+e.getMessage());
        }
        return false;
    }
}
