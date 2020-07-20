package com.resto.authService.response;

import java.util.List;

public class JWTResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private Long personId;
	private String username;
	private List<String> roles;

	public JWTResponse(String token, Long id, Long personId, String username, List<String> roles) {
		this.token = token;
		this.id = id;
		this.personId = personId;
		this.username = username;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
