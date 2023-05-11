package fa.cineverse.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int customerId;
	@Column(name="full_name",columnDefinition = "NVARCHAR(255)",nullable = false)
	private String fullName;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String address;
	@Column(name="phone_number",length=12,nullable = false)
	private String phoneNumber;
	@Column(columnDefinition = "DATE")
	private LocalDate birthday;
	private boolean gender;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User userId;
	
	public Customer() {
		super();
	}
	
	public Customer(int customerId, String fullName, String address, String phoneNumber, LocalDate birthday,
			boolean gender, User user) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.gender = gender;
		this.userId = user;
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User user) {
		this.userId = user;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
}
