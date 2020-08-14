package com.resto.authService.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.authService.dao.UserDAO;
import com.resto.authService.jwt.JWTUtils;
import com.resto.authService.request.LoginRequest;
import com.resto.authService.response.JWTResponse;
import com.resto.authService.service.UserPrincipal;
import com.resto.commonModel.entity.User;
import com.resto.commonModel.exception.IncorrectAuthorizationException;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JWTUtils jwtUtils;

	@Autowired
	UserDAO userDAO;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/auth/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJWTToken(authentication);

			UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JWTResponse(jwt, userDetails.getId(), null, userDetails.getUsername(), roles));
		} catch (DisabledException e) {
			System.out.println(e);
			throw new IncorrectAuthorizationException("User has been deactivated. Contact Administrator");
		} catch (Exception e) {
			System.out.println(e);
			throw new IncorrectAuthorizationException("The provided credentials are incorrect");
		}
	}

	@PostMapping("/auth/register")
	public void registerUser(@RequestBody User user, BindingResult bindingResult) {
		if(user.getPassword() != null) {
			user.setPassword("{bcrypt}" + encoder.encode(user.getPassword()));
		}
		user.setEnabled(true);
		userDAO.save(user);
	}

	@GetMapping("/test")
	public String test() {
		return "test";
	}

}
