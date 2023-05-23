/**
 * Created At {14 May 2023
 * By HuuNQ
 */
package fa.cineverse.dto;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fa.cineverse.model.Customer;



/**
 * @author HuuNQ
 *
 * 14 May 2023
 * 
 */
public class UserDTO implements Validator{
//	private String userId;
	private String fullName;
	private LocalDate birthday;
	private String phoneNumber;
	private boolean gender;
	private String username;
	private String password;
	private String address;
	
	public UserDTO() {
		super();
	}
	public UserDTO(String fullName, LocalDate birthday, String phoneNumber, boolean gender, String username,
			String password, String address) {
		super();
		this.fullName = fullName;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.address = address;
	}
	
//	public UserDTO(String userId, String fullName, LocalDate birthday, String phoneNumber, boolean gender,
//			String username, String password, String address) {
//		super();
//		this.userId = userId;
//		this.fullName = fullName;
//		this.birthday = birthday;
//		this.phoneNumber = phoneNumber;
//		this.gender = gender;
//		this.username = username;
//		this.password = password;
//		this.address = address;
//	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Customer.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserDTO customerDTO = (UserDTO) target;
		
		if("".equals(customerDTO.getUsername().trim())) {
			errors.rejectValue("email", "email error","Không được bỏ trống!");
		}
		
		if("".equals(customerDTO.getFullName().trim())) {
			errors.rejectValue("email", "email error","Không được bỏ trống!");
		}
		
		if("".equals(customerDTO.getPassword().trim())) {
			errors.rejectValue("email", "email error","Không được bỏ trống!");
		}
		
		if("".equals(customerDTO.getPhoneNumber().trim()) || customerDTO.getPhoneNumber() == null) {
			errors.rejectValue("email", "email error","Không được bỏ trống!");
		}
		
		if(customerDTO.getBirthday() == null) {
			errors.rejectValue("email", "email error","Không được bỏ trống!");
		}
	}

}
