package fa.cineverse.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_tbl")
@JsonIgnoreProperties({"customer","userRole"})
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "NVARCHAR(255)")
	private String username;
	
	private String password;
	
	@Column(name="created_date",columnDefinition = "DATETIME")
	private LocalDateTime createdDate;
	
	@Column(name="update_at",columnDefinition = "DATETIME")
	private LocalDateTime updateAt;
	@Column(name="last_login",columnDefinition = "DATETIME")
	private LocalDateTime lastLogin;
	@Column(name="is_delete")
	private boolean isDelete;
	@Column(name="reset_password_token",columnDefinition="VARCHAR(25)")
	private String resetPasswordToken;
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<UserRole> userRole;
	
	@OneToOne(mappedBy = "user")
	@JoinColumn(name="username")
	private Customer customer;
	
	public User() {
		
	}

	public User(String username, String password, LocalDateTime createdDate, LocalDateTime updateAt,
			LocalDateTime lastLogin, boolean isDelete, String resetPasswordToken, List<UserRole> userRole,
			Customer customer) {
		super();
		this.username = username;
		this.password = password;
		this.createdDate = createdDate;
		this.updateAt = updateAt;
		this.lastLogin = lastLogin;
		this.isDelete = isDelete;
		this.resetPasswordToken = resetPasswordToken;
		this.userRole = userRole;
		this.customer = customer;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return getUserRole().stream().map(x-> new SimpleGrantedAuthority(x.getRole().getRoleName())).collect(Collectors.toList());
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


}
