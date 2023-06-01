/**
 * Created At {11 May 2023
 * By HuuNQ
 */
package fa.cineverse.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
* LoginRequest
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
public class LoginRequest implements Validator{
	private String username;
	private String password;
	private final Pattern EMAIL_REG = 
			Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])+",Pattern.CASE_INSENSITIVE);
	private final Pattern PASSWORD_REG = Pattern.compile("[a-zA-Z0-9]*");
	public LoginRequest() {
		super();
	}
	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LoginRequest loginRequest = (LoginRequest) target;
		if("".equals(loginRequest.getUsername().trim())) {
			errors.rejectValue("username", username, "Vui lòng không để trống!");
		}else if(!EMAIL_REG.matcher(loginRequest.getUsername()).matches()) {
			errors.rejectValue("username", username, "Vui lòng nhập đúng định dạng Email!");
		}
		
		if("".equals(loginRequest.getPassword().trim())){
			errors.rejectValue("password", "password","Vui lòng không để trống!");
		}else if(loginRequest.getPassword().length()<6) {
			errors.rejectValue("password", "password","Mật khẩu phải từ 6 ký tự!");
		}else if(loginRequest.getPassword().length()>30) {
			errors.rejectValue("password", "password","Mật khẩu phải ít hơn 30 ký tự!");
		}else if(!PASSWORD_REG.matcher(loginRequest.getPassword()).matches()) {
			errors.rejectValue("password", "password", "Mật khẩu không được chứa kí tự đặc biệt");
		}
	}
	
	
}
