package com.taskmanagementproject.payload;

public class JWTAuthResponse {

	private String token;
	private String tokenType = "Bearer";

	public JWTAuthResponse(String token) {
		this.token = token;
	}

	public String getToken() { return token; }
	public String getTokenType() { return tokenType; }
}