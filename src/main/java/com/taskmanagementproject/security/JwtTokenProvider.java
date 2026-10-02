package com.taskmanagementproject.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private long jwtExpirationMs;

	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(Authentication authentication) {
		String email = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + jwtExpirationMs);

		return Jwts.builder()
				.subject(email)
				.issuedAt(currentDate)
				.expiration(expireDate)
				.signWith(getKey())
				.compact();
	}

	public String getEmailFromToken(String token) {
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}