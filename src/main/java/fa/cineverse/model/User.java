package fa.cineverse.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_tbl")
public class User {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(columnDefinition = "NVARCHAR(255)",unique = true,nullable = false)
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
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<UserRole> userRole;
	
	@OneToOne(mappedBy = "userId")
	@JoinColumn(name="user_id")
	private Customer customer;
	
	public User() {
		
	}

	public User(int userId, String username, String password, LocalDateTime createdDate, LocalDateTime updateAt,
			LocalDateTime lastLogin, boolean isDelete) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.createdDate = createdDate;
		this.updateAt = updateAt;
		this.lastLogin = lastLogin;
		this.isDelete = isDelete;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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


}
