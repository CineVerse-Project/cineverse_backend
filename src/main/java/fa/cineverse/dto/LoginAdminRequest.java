/**
 * Created At {12 May 2023
 * By HuuNQ
 */
package fa.cineverse.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author HuuNQ
 *
 * 12 May 2023
 * 
 */
public class LoginAdminRequest implements Validator{
	private String username;
	private String password;
	private final Pattern ACCOUNT_REG = 
			Pattern.compile("[a-zA-Z0-9]*");
	private final Pattern PASSWORD_REG = Pattern.compile("[a-zA-Z0-9]*");
	public LoginAdminRequest() {
		super();
	}
	public LoginAdminRequest(String username, String password) {
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
		LoginAdminRequest loginRequest = (LoginAdminRequest) target;
		if("".equals(loginRequest.getUsername().trim())) {
			errors.rejectValue("username", username, "Vui lòng không để trống!");
		}else if(!ACCOUNT_REG.matcher(loginRequest.getUsername()).matches()) {
			errors.rejectValue("username", username, "Tài khoản không được chứa kí tự đặc biệt!");
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
