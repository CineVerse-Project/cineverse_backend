/**
 * Created At {22 May 2023
 * By HuuNQ
 */
package fa.cineverse.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author HuuNQ
 *
 * 22 May 2023
 * 
 */
public class ForgotPasswordRequest implements Validator {
	private String username;

	public ForgotPasswordRequest() {
		super();
	}

	public ForgotPasswordRequest(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ForgotPasswordRequest forgotPasswordRequest = (ForgotPasswordRequest) target;
		
	}
	
	
}
