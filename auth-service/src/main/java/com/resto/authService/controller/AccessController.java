package com.resto.authService.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AccessController {
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/reception")
	@PreAuthorize("hasRole('RECEPTION') or hasRole('ADMIN')")
	public String receptionAccess() {
		return "Reception";
	}

	@GetMapping("/manager")
	@PreAuthorize("hasRole('MANAGER') or hasRole('RECEPTION') or hasRole('ADMIN')")
	public String managerAccess() {
		return "Manager";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin";
	}
}
