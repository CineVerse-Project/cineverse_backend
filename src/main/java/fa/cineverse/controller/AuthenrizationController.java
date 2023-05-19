/**
 * Created At {12 May 2023
 * By HuuNQ
 */
package fa.cineverse.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.TextNode;

import fa.cineverse.common.JwtCommon;
import fa.cineverse.dto.ChangePasswordRequest;
import fa.cineverse.dto.CustomUserDetails;
import fa.cineverse.dto.JwtResponse;
import fa.cineverse.dto.LoginAdminRequest;
import fa.cineverse.dto.LoginRequest;
import fa.cineverse.dto.ResetPasswordRequest;
import fa.cineverse.model.User;
import fa.cineverse.service.EmailService;
import fa.cineverse.service.UserService;
import freemarker.template.TemplateException;
import net.bytebuddy.utility.RandomString;

/**
 * @author HuuNQ
 *
 *         12 May 2023
 * 
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AuthenrizationController {
	
	@Autowired
	private JwtCommon jwtCommon;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	/**
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:17:05
	 * @Return: ResponseEntity<?>
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<?> userLoginRequest(@Valid @RequestBody LoginRequest loginRequest,
			BindingResult bindingResult) {
		new LoginRequest().validate(loginRequest, bindingResult);
		Map<String, String> errorMap = new HashMap<>();
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(x -> errorMap.put(x.getField(), x.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errorMap);
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtGenerate = jwtCommon.generateToken(authentication);
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		List<String> userRoles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwtGenerate, loginRequest.getUsername(), userRoles));
	}

	/**
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:17:02
	 * @Return: ResponseEntity<?>
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public ResponseEntity<?> adminLoginRequest(@Valid @RequestBody LoginAdminRequest loginAdminRequest,
			BindingResult bindingResult) {
		new LoginAdminRequest().validate(loginAdminRequest, bindingResult);
		Map<String, String> errorMap = new HashMap<>();
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(x -> errorMap.put(x.getField(), x.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errorMap);
		}
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginAdminRequest.getUsername(), loginAdminRequest.getPassword()));
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtGenerate = jwtCommon.generateToken(authentication);
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		List<String> userRoles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwtGenerate, loginAdminRequest.getUsername(), userRoles));
	}

	@RequestMapping(value = "/user/change-password", method = RequestMethod.POST)
	public ResponseEntity<?> user(HttpServletRequest request,@Valid @RequestBody ChangePasswordRequest changePassword, BindingResult bindingResult) {
		new ChangePasswordRequest().validate(changePassword, bindingResult);
		Map<String, String> errorMap = new HashMap<>();
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(
					x -> errorMap.put(x.getCode(), x.getDefaultMessage())
					);
			
			return ResponseEntity.badRequest().body(errorMap);
		}
		String tokenString = request.getHeader("Authorization");
		String token = null;
		if(StringUtils.hasText(tokenString) && tokenString.startsWith("Bearer ") ){
			token = tokenString.substring(7,tokenString.length());
        }
		String username = jwtCommon.getUsernameFromToken(token);
		if(changePassword.getUsername().equals(username)) {
			User user = userService.findByUsername(changePassword.getUsername());
			if(user!=null) {
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				boolean checkPassword = passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword());
				if(checkPassword) {
					user.setPassword(changePassword.getNewPassword());
					userService.saveUser(user);
				}else {
					errorMap.put("oldPassword", "Sai mật khẩu!");
				}
			}
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.badRequest().build();
	}

	/**
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:16:58
	 * @Return: ResponseEntity<?>
	 */
	@RequestMapping(value = "/admin/admin", method = RequestMethod.GET)
	public ResponseEntity<?> admin() {
		return ResponseEntity.ok("OK");
	}

	/**
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:16:55
	 * @Return: ResponseEntity<?>
	 */
	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
		if ("".equals(email) || email == null) {
			return ResponseEntity.badRequest().body("Khong bo trong");
		}
		String usernameString = email.toString();
		User user = userService.findByUsername(usernameString);
		if (user != null) {
			

				String resetPassword = RandomString.make(20);
				user.setResetPasswordToken(resetPassword);
				userService.createResetPassword(user);
				try {
					emailService.sendEmail(usernameString,
							"http://localhost:3000/reset-password?reset-password-token=" +resetPassword+ "&username="+usernameString
					);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TemplateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		return ResponseEntity.ok("OK");
	}

	/**
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:16:51
	 * @Return: ResponseEntity<?>
	 */
	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPassword,BindingResult result) {
		new ResetPasswordRequest().validate(resetPassword, result);
		Map<String, String> errorMap = new HashMap<>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(
					x -> errorMap.put(x.getCode(), x.getDefaultMessage())
					);
			
			return ResponseEntity.badRequest().body(errorMap);
		}
		User user = userService.findByUsername(resetPassword.getUsernameString());
		if(user!= null && user.getResetPasswordToken().equals(resetPassword.getTokenString())) {
			String encodePassword = resetPassword.getNewPasswordString();
			user.setPassword(encodePassword);
			user.setResetPasswordToken(null);
			userService.saveUser(user);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
}
