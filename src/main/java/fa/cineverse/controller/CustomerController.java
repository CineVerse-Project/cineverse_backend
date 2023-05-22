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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	/**
	 * @Author: HuuNQ
	 * @Day: 19 May 2023 | @Time: 14:16:58
	 * @Return: ResponseEntity<?>
	 */
	@RequestMapping(value = "/user/history-order", method = RequestMethod.GET)
	public ResponseEntity<?> historyOrder(@RequestParam("username")String username) {
		User user = userService.findByUsername(username);
		if(user!=null) {
			Customer customer = customerService.findByUser(user);
			List<Object[]> historyOrder = customerService.allHistoryOrderByCustomer(customer);
			return ResponseEntity.ok().body(historyOrder);
		}
		return ResponseEntity.badRequest().build();
		
	}
	
	@RequestMapping(value = "/user/earn-points", method = RequestMethod.GET)
	public ResponseEntity<?> earnPoints(@RequestParam("username")String username) {
		User user = userService.findByUsername(username);
		if(user!=null) {
			Customer customer = customerService.findByUser(user);
			List<Object[]> earnPoints = customerService.listEarnPoints(customer);
			
			return ResponseEntity.ok().body(earnPoints);
		}
		return ResponseEntity.badRequest().build();
		
	}
}
