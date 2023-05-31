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
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.cineverse.common.JwtCommon;
import fa.cineverse.dto.ChangePasswordRequest;
import fa.cineverse.dto.ForgotPasswordRequest;
import fa.cineverse.dto.JwtResponse;
import fa.cineverse.dto.LoginAdminRequest;
import fa.cineverse.dto.LoginRequest;
import fa.cineverse.dto.ResetPasswordRequest;
import fa.cineverse.dto.UserDTO;
import fa.cineverse.model.Customer;
import fa.cineverse.model.User;
import fa.cineverse.service.CustomerService;
import fa.cineverse.service.EmailService;
import fa.cineverse.service.UserService;
import freemarker.template.TemplateException;
import net.bytebuddy.utility.RandomString;

/**
* UserController
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
@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private JwtCommon jwtCommon;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * userLoginRequest
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:17:05
	 * @param loginRequest 
     * @param bindingResult 
	 * @return ResponseEntity<?>
	 */
	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public ResponseEntity<?> userLoginRequest(@Valid @RequestBody LoginRequest loginRequest,
			BindingResult bindingResult) {
		new LoginRequest().validate(loginRequest, bindingResult);
		Map<String, String> errorMap = new HashMap<>();
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(x -> errorMap.put(x.getField(), x.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errorMap);
		}
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtGenerate = jwtCommon.generateToken(authentication);
		UserDetails user = (UserDetails) authentication
				.getPrincipal();
		List<String> userRoles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwtGenerate, loginRequest.getUsername(), userRoles));
	}

	/**
	 * adminLoginRequest
     * @Author: HuuNQ
     * @Day: 19 May 2023 | @Time: 14:17:02
	 * @param loginAdminRequest 
	 * @param bindingResult 
     * @return  ResponseEntity<?>
	 */
	@RequestMapping(value = "/sign-in/admin", method = RequestMethod.POST)
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
		String jwtGenerate = jwtCommon.generateToken(authentication);
		UserDetails user = (UserDetails) authentication
				.getPrincipal();
		List<String> userRoles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwtGenerate, loginAdminRequest.getUsername(), userRoles));
	}

	/**
	 * forgotPassword
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:16:55
	 * @param forgotPasswordRequest 
     * @param bindingResult 
	 * @return  ResponseEntity<?>
	 */
	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest,
			BindingResult bindingResult) {
		new ForgotPasswordRequest().validate(forgotPasswordRequest, bindingResult);
		Map<String, String> errorMap = new HashMap<>();

		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(x -> {
				errorMap.put(x.getCode(), x.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(errorMap);
		}

		User user = userService.findByUsername(forgotPasswordRequest.getUsername());

		if (user != null) {
			String resetPassword = RandomString.make(20);
			user.setResetPasswordToken(resetPassword);
			userService.createResetPassword(user);
			try {
				emailService.sendEmail(forgotPasswordRequest.getUsername(),
						"http://localhost:3000/reset-password?reset-password-token=" + resetPassword + "&username="
								+ forgotPasswordRequest.getUsername());
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
			return ResponseEntity.ok("Gửi yêu cầu thành công, vui lòng kiểm tra mail!");
		}
		return ResponseEntity.badRequest().body("Tài khoản này chưa tồn tại trong hệ thống!");

	}

    /**
     * resetPassword
     * @Author: HuuNQ
     * @Day: 19 May 2023 | @Time: 14:16:55
     * @param forgotPasswordRequest 
     * @param username
     * @param token 
     * @param resetPassword 
     * @param result 
     * @return  ResponseEntity<?>
     */
	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestParam("username") Optional<String> username,
			@RequestParam("reset-password-token") Optional<String> token,
			@Valid @RequestBody ResetPasswordRequest resetPassword, BindingResult result) {
		if (!username.isPresent() || !token.isPresent()) {
			return ResponseEntity.badRequest().body("Yêu cầu chưa hợp lệ!");
		}

		String usernamePresent = null;
		if (username.isPresent()) {
			usernamePresent = username.get();
		}
		String tokenPresent = null;
		if (token.isPresent()) {
			tokenPresent = token.get();
		}

		new ResetPasswordRequest().validate(resetPassword, result);
		Map<String, String> errorMap = new HashMap<>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(x -> errorMap.put(x.getCode(), x.getDefaultMessage()));

			return ResponseEntity.badRequest().body(errorMap);
		}

		User user = userService.findByUsername(usernamePresent);
		if (user != null && tokenPresent.equals(user.getResetPasswordToken())) {
			user.setPassword(resetPassword.getNewPassword());
			user.setResetPasswordToken(null);
			userService.updateUser(user);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

    /**
     * resetPassword
     * @Author: HuuNQ
     * @Day: 19 May 2023 | @Time: 14:16:55
     * @param userDTO 
     * @param bindingResult 
     * @return  ResponseEntity<?>
     */
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		new UserDTO().validate(userDTO, bindingResult);
		Map<String, String> errorMap = new HashMap<>();
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(x -> errorMap.put(x.getField(), x.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errorMap);
		}
		// Kiểm tra dữ liệu email đã tồn tại trong database chưa.
		User users = userService.findByUsername(userDTO.getUsername());
		if (users == null) {
			User user = new User();
			BeanUtils.copyProperties(userDTO, user);
			userService.saveUser(user);
			Customer customer = new Customer(userDTO.getFullName(), userDTO.getUsername(), userDTO.getAddress(),
					userDTO.getPhoneNumber(), userDTO.getBirthday(), userDTO.isGender());
			customer.setUser(user);
			customerService.saveCustomer(customer);
			return ResponseEntity.ok().body("Đăng ký thành công");
		}
		return new ResponseEntity<>("Email đã được đăng ký", HttpStatus.CONFLICT);
//		return ResponseEntity.created(HttpStatus.CONFLICT).body("Email đã được đăng ký");
	}

	/**
     * information
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:16:58
     * @param username 
     * @param request 
	 * @return ResponseEntity<?>
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/user/{username}")
	public ResponseEntity<?> information(@PathVariable("username") String username, HttpServletRequest request) {
		// Không tìm thấy người dùng? trường hợp nhập bậy // kiểm tra người dùng cùng
		// token gửi về
		String tokenString = request.getHeader("Authorization");
		String token = null;
		if (StringUtils.hasText(tokenString) && tokenString.startsWith("Bearer ")) {
			token = tokenString.substring(7, tokenString.length());
		}
		if (!jwtCommon.validateJwtToken(token)) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		String usernameToken = jwtCommon.getUsernameFromToken(token);
		if (!username.equals(usernameToken)) {
			return ResponseEntity.badRequest().body("Sai thông tin đăng nhập!");
		}
		Customer customer = customerService.findCustomerByUser(username);
		if (customer != null) {
			return ResponseEntity.ok().body(customer);
		}
		return ResponseEntity.notFound().build();

	}

	/**
	 * updateInformation
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:16:58
	 * @param userDTO 
     * @param bindingResult 
     * @param request
     * @return  ResponseEntity<?>
	 */
	@PatchMapping("/user/profile-update")
	public ResponseEntity<?> updateInformation(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult,
			HttpServletRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(x -> errorMap.put(x.getField(), x.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errorMap);
		}
		String tokenString = request.getHeader("Authorization");
		String token = null;
		if (StringUtils.hasText(tokenString) && tokenString.startsWith("Bearer ")) {
			token = tokenString.substring(7, tokenString.length());
		}
		if (!jwtCommon.validateJwtToken(token)) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		String usernameToken = jwtCommon.getUsernameFromToken(token);
		if (!userDTO.getUsername().equals(usernameToken)) {
			return ResponseEntity.badRequest().body("Sai thông tin đăng nhập!");
		}
		User user = userService.findByUsername(userDTO.getUsername());
		if (user != null) {
			Customer customer = customerService.findByUser(user);
			boolean checkMatchs = passwordEncoder.matches(userDTO.getPassword(), customer.getUser().getPassword());
			if (checkMatchs) {
				BeanUtils.copyProperties(userDTO, customer);
				customerService.updateCustomer(customer);
				return ResponseEntity.ok("Cập nhật thành công");
			}
		}

		return ResponseEntity.notFound().build();
	}

	/**
	 * changePassword
	 * @Author: HuuNQ
	 * @Day: 22 May 2023 | @Time: 08:42:06
	 * @param username 
     * @param request 
     * @param changePassword 
     * @param bindingResult 
     * @return  ResponseEntity<?>
	 */
	@RequestMapping(value = "/user/change-password", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestParam("username") String username, HttpServletRequest request,
			@Valid @RequestBody ChangePasswordRequest changePassword, BindingResult bindingResult) {
		new ChangePasswordRequest().validate(changePassword, bindingResult);
		Map<String, String> errorMap = new HashMap<>();
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(x -> errorMap.put(x.getCode(), x.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errorMap);
		}
		String tokenString = request.getHeader("Authorization");
		String token = null;
		if (StringUtils.hasText(tokenString) && tokenString.startsWith("Bearer ")) {
			token = tokenString.substring(7, tokenString.length());
		}
		if (!jwtCommon.validateJwtToken(token)) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		String usernameToken = jwtCommon.getUsernameFromToken(token);

		if (username.equals(usernameToken)) {
			User user = userService.findByUsername(username);
			if (user != null) {
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				boolean checkPassword = passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword());
				if (checkPassword) {
					user.setPassword(changePassword.getNewPassword());
					userService.updateUser(user);
					return ResponseEntity.ok().body("Đổi mật khẩu thành công!");
				} else {
					errorMap.put("oldPassword", "Sai mật khẩu!");
					return ResponseEntity.badRequest().body(errorMap);
				}
			} else {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.badRequest().body("Sai thông tin đăng nhập!");
	}

	/**
	 * historyOrder
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:16:58
	 * @param username 
     * @param request 
	 * @return ResponseEntity<?>
	 */
	@RequestMapping(value = "/user/order-history", method = RequestMethod.GET)
	public ResponseEntity<?> historyOrder(@RequestParam("username") String username, HttpServletRequest request) {
		User user = userService.findByUsername(username);

		if (user != null) {
			Customer customer = customerService.findByUser(user);
			List<Object[]> historyOrder = customerService.allHistoryOrderByCustomer(customer);
			return ResponseEntity.ok().body(historyOrder);
		}

		return ResponseEntity.badRequest().build();

	}

	/**
	 * @Author: HuuNQ
	 * @Day: 23 May 2023 | @Time: 08:19:11
	 * @param username 
     * @param request 
	 * @return ResponseEntity<?>
	 */
	@RequestMapping(value = "/user/earn-points", method = RequestMethod.GET)
	public ResponseEntity<?> earnPoints(@RequestParam("username") String username, HttpServletRequest request) {

		User user = userService.findByUsername(username);

		if (user != null) {
			Customer customer = customerService.findByUser(user);
			List<Object[]> earnPoints = customerService.listEarnPoints(customer);
			return ResponseEntity.ok().body(earnPoints);
		}

		return ResponseEntity.badRequest().build();

	}
}
