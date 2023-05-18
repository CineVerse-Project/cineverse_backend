package fa.cineverse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fa.cineverse.common.JwtCommon;
import fa.cineverse.dto.UserDTO;
import fa.cineverse.model.Customer;
import fa.cineverse.model.User;
import fa.cineverse.service.CustomerService;
import fa.cineverse.service.UserService;

/**
 * @author HuuNQ
 *
 * 18 May 2023
 * 
 */
@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private JwtCommon jwtCommon;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/customer/register")
	public ResponseEntity<?> signUp(@Valid @RequestBody UserDTO userDTO,BindingResult bindingResult){
		new UserDTO().validate(userDTO, bindingResult);
		Map<String,String> errorMap = new HashMap<>();
		if(bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(x->errorMap.put(x.getField(), x.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errorMap);

		}
		//Kiểm tra dữ liệu email đã tồn tại trong database chưa.
		List<User> users = userService.findUserByUsername(userDTO.getUsername());
		if(users.isEmpty()) {
			User user = new User();
			BeanUtils.copyProperties(userDTO, user);
			userService.saveUser(user);
			Customer customer = new Customer(userDTO.getFullName(),userDTO.getUsername(),userDTO.getAddress(),userDTO.getPhoneNumber(),userDTO.getBirthday(),userDTO.isGender());
			customer.setUser(user);
			customerService.saveCustomer(customer);
			return ResponseEntity.ok("Đăng ký thành công!");
		}

		return ResponseEntity.badRequest().body("Email đã được đăng ký!");
	}
	
	
	@GetMapping("/customer/{username}")
	public ResponseEntity<?> information(@PathVariable("username") String username){
		//Không tìm thấy người dùng? trường hợp nhập bậy // kiểm tra người dùng cùng token gửi về
		Customer customer = customerService.findByUsername(username);
		if(customer!=null) {
			return ResponseEntity.ok(customer);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PatchMapping("/customer/customer-update")
	public ResponseEntity<?> updateInformation(@Valid @RequestBody UserDTO userDTO,BindingResult bindingResult,HttpServletRequest request){
		Map<String, String> errorMap = new HashMap<>();
		if(bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(x->errorMap.put(x.getField(), x.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errorMap);
		}
		Customer customer = customerService.findByUsername(userDTO.getUsername());
		boolean checkMatchs = passwordEncoder.matches(userDTO.getPassword(),customer.getUser().getPassword());
		if(checkMatchs){
			BeanUtils.copyProperties(userDTO, customer);
			customerService.updateCustomer(customer);
			return ResponseEntity.ok(customer);
		}
		return ResponseEntity.notFound().build();
	}

}
