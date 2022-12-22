package com.keyVault.app.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.repository.query.QueryLookupStrategy.Key;

import io.jsonwebtoken.Jwts;

public class TokenUtils {
	/*private final static String ACCESS_TOKEN_SECRET = "";
	private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
	
	public static String createToken(String nombre, String email) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
		Date expirationDate = new Date(System.currentTimeMillis()* expirationTime);
		
		Map<String, Object> extra = new HashMap<>();
		extra.put("nombre", nombre);
		
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(Keys.hmacShakeyFor(ACCESS_TOKEN_SECRET.getBytes()))
	}*/
}
