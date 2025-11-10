package com.inquiry.loan.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Modern JwtUtil using io.jsonwebtoken Keys.hmacShaKeyFor and parserBuilder.
 * This avoids any use of javax.xml.bind.DatatypeConverter and works on Java
 * 11+.
 */
@Component
public class JwtUtil {

	// Use a long random secret (at least 32 bytes for HS256). Replace with
	// env/config in production.
	private final String SECRET = "replace_with_a_long_random_secret_of_at_least_32_bytes!";
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

	private final long EXPIRATION = 1000L * 60 * 60 * 8; // 8 hours

	public String generateToken(String username, String role) {
		long now = System.currentTimeMillis();
		Date issued = new Date(now);
		Date expiry = new Date(now + EXPIRATION);

		return Jwts.builder().setSubject(username).claim("role", role).setIssuedAt(issued).setExpiration(expiry)
				.signWith(SECRET_KEY, SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public String extractRole(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
		Object r = claims.get("role");
		return r == null ? null : r.toString();
	}

	public boolean validateToken(String token, String username) {
		try {
			final String u = extractUsername(token);
			return (u != null && u.equals(username) && !isTokenExpired(token));
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isTokenExpired(String token) {
		Date exp = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody()
				.getExpiration();
		return exp.before(new Date());
	}
}
