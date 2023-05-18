/**
 * Created At {12 May 2023
 * By HuuNQ
 */
package fa.cineverse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.cineverse.common.JwtCommon;
import fa.cineverse.dto.CustomUserDetails;
import fa.cineverse.dto.JwtResponse;
import fa.cineverse.dto.LoginAdminRequest;
import fa.cineverse.dto.LoginRequest;
import fa.cineverse.model.Role;
import fa.cineverse.model.User;
import fa.cineverse.model.UserRole;
import fa.cineverse.service.impl.CustomUserDetailsService;

/**
 * @author HuuNQ
 *
 * 12 May 2023
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
	

	@RequestMapping(value="/user/login",method = RequestMethod.POST)
	public ResponseEntity<?> userLoginRequest(@Valid @RequestBody LoginRequest loginRequest,BindingResult bindingResult) {
		new LoginRequest().validate(loginRequest, bindingResult);
		Map<String,String> errorMap = new HashMap<>();
		if(bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(x->errorMap.put(x.getField(), x.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errorMap);
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
				);
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtGenerate = jwtCommon.generateToken(authentication);
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		List<String> userRoles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwtGenerate,loginRequest.getUsername(),userRoles));
	}
	
	@RequestMapping(value="/admin/login",method = RequestMethod.POST)
	public ResponseEntity<?> adminLoginRequest(@Valid @RequestBody LoginAdminRequest loginAdminRequest,BindingResult bindingResult) {
		new LoginAdminRequest().validate(loginAdminRequest, bindingResult);
		Map<String,String> errorMap = new HashMap<>();
		if(bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(x->errorMap.put(x.getField(), x.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errorMap);
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginAdminRequest.getUsername(), loginAdminRequest.getPassword())
				);
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtGenerate = jwtCommon.generateToken(authentication);
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		List<String> userRoles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwtGenerate,loginAdminRequest.getUsername(),userRoles));
	}
	
	@RequestMapping(value="/user/user",method = RequestMethod.GET)
	public ResponseEntity<?> user() {
		return ResponseEntity.ok("OK");
	}
	
	@RequestMapping(value="/admin/admin",method = RequestMethod.GET)
	public ResponseEntity<?> admin() {
		return ResponseEntity.ok("OK");
	}
	
	@RequestMapping(value="/forgot-password",method = RequestMethod.POST)
	public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
		
		return ResponseEntity.ok("OK");
	}
	
	@RequestMapping(value="/admin/account",method = RequestMethod.GET)
	public ResponseEntity<?> accountAdmin() {
		return ResponseEntity.ok("OK");
	}
}
