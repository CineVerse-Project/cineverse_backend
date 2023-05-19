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
	private String usernameString;
	private String tokenString;
	private String newPasswordString;
	private String confirmNewPasswordString;
	
	
	public ResetPasswordRequest() {
		super();
	}


	public ResetPasswordRequest(String usernameString, String tokenString, String newPasswordString,
			String confirmNewPasswordString) {
		super();
		this.usernameString = usernameString;
		this.tokenString = tokenString;
		this.newPasswordString = newPasswordString;
		this.confirmNewPasswordString = confirmNewPasswordString;
	}


	public String getUsernameString() {
		return usernameString;
	}


	public void setUsernameString(String usernameString) {
		this.usernameString = usernameString;
	}


	public String getTokenString() {
		return tokenString;
	}


	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}


	public String getNewPasswordString() {
		return newPasswordString;
	}


	public void setNewPasswordString(String newPasswordString) {
		this.newPasswordString = newPasswordString;
	}


	public String getConfirmNewPasswordString() {
		return confirmNewPasswordString;
	}


	public void setConfirmNewPasswordString(String confirmNewPasswordString) {
		this.confirmNewPasswordString = confirmNewPasswordString;
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
		if(!passwordRequest.getNewPasswordString().equals(passwordRequest.getConfirmNewPasswordString())) {
			errors.rejectValue("confirmNewPasswordString", "Xác nhận mật khẩu không chính xác!");
		}
	}
	
	
}
