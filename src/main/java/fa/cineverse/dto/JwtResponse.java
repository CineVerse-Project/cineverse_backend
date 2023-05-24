/**
 * Created At {12 May 2023
 * By HuuNQ
 */
package fa.cineverse.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import fa.cineverse.model.Role;

/**
 * @author HuuNQ
 *
 * 12 May 2023
 * 
 */
public class JwtResponse {
	private String type = "Bearer";
	private String token;
	private String username;
	private List<String> roles;
	
	public JwtResponse() {
		super();
	}

	public JwtResponse(String token,String username, List<String> roles) {
		super();
		this.token = token;
		this.username = username;
		this.roles = roles;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
