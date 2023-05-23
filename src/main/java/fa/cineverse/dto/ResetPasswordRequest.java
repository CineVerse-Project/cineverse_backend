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
 * 19 May 2023
 * 
 */
public class ResetPasswordRequest implements Validator {
	private String newPassword;
	private String confirmPassword;
	
	
	public ResetPasswordRequest() {
		super();
	}

	public ResetPasswordRequest(String newPassword, String confirmNewPassword) {
		super();
		this.newPassword = newPassword;
		this.confirmPassword = confirmNewPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getConfirmNewPassword() {
		return confirmPassword;
	}


	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmPassword = confirmNewPassword;
	}


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ResetPasswordRequest passwordRequest = (ResetPasswordRequest) target;
		if(!passwordRequest.getNewPassword().equals(passwordRequest.getConfirmNewPassword())) {
			errors.rejectValue("confirmNewPasswordString", "Xác nhận mật khẩu không chính xác!","Xác nhận mật khẩu không chính xác!");
		}
	}
	
	
}
