/**
 * Created At {19 May 2023
 * By HuuNQ
 */
package fa.cineverse.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author HuuNQ
 *
 *         19 May 2023
 * 
 */
public class ChangePasswordRequest implements Validator {
	private String username;
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;

	public ChangePasswordRequest() {
		super();
	}

	public ChangePasswordRequest(String username, String oldPassword, String newPassword, String confirmNewPassword) {
		super();
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ChangePasswordRequest changePasswordRequest = (ChangePasswordRequest) target;
		
	}

}
