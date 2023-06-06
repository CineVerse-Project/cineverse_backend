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
* UserDTO
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
public class UserDTO implements Validator{
	private String customerId;
	private String fullName;
	private LocalDate birthday;
	private String phoneNumber;
	private boolean gender;
	private String username;
	private String password;
	private String address;
	private String imgUrl;
	
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
	
	public UserDTO(String customerId, String fullName, LocalDate birthday, String phoneNumber, boolean gender,
			String username, String password, String address, String imgUrl) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.address = address;
		this.imgUrl = imgUrl;
	}
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

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
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
			errors.rejectValue("fullName", "fullName error","Không được bỏ trống!");
		}
		
		if("".equals(customerDTO.getPassword().trim())) {
			errors.rejectValue("password", "password error","Không được bỏ trống!");
		}
		
		if("".equals(customerDTO.getPhoneNumber().trim()) || customerDTO.getPhoneNumber() == null) {
			errors.rejectValue("phoneNumber", "phoneNumber error","Không được bỏ trống!");
		}
		
		if(customerDTO.getBirthday() == null) {
			errors.rejectValue("birthday", "birthday error","Không được bỏ trống!");
		}
	}

}
