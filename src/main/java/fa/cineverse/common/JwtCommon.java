/**
 * Created At {13 May 2023
 * By HuuNQ
 */
package fa.cineverse.common;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author HuuNQ
 *
 * 13 May 2023
 * 
 */
@Component
public class JwtCommon {
	private final String secretKey = "cineverse";
    private final Integer expiredTokenMs = 360_000;

    public String generateToken(Authentication authentication){
    	org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expiredTokenMs))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
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
