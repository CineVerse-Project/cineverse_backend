/**
 * Created At {13 May 2023
 * By HuuNQ
 */
package fa.cineverse.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fa.cineverse.model.User;
import fa.cineverse.model.UserRole;

/**
 * @author HuuNQ
 *
 * 13 May 2023
 *
 */
public class CustomUserDetails implements UserDetails {
	/**
	 *
	 */
	private static final long serialVersionUID = -4074917036430799201L;
	private User user;
	private List<GrantedAuthority> grantedAuthority;


	public CustomUserDetails() {
		super();
	}

	public CustomUserDetails(User user, List<GrantedAuthority> grantedAuthority) {
		super();
		this.user = user;
		this.grantedAuthority = grantedAuthority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<UserRole> userRole = user.getUserRole();
		List<GrantedAuthority> granted = new ArrayList<>();
		userRole.forEach(x->granted.add(new SimpleGrantedAuthority(x.getRole().getRoleName())));
		return granted;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CustomUserDetails))
			return false;
		CustomUserDetails other = (CustomUserDetails) obj;
		return Objects.equals(user, other.user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<GrantedAuthority> getGrantedAuthority() {
		return grantedAuthority;
	}

	public void setGrantedAuthority(List<GrantedAuthority> grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}



}
