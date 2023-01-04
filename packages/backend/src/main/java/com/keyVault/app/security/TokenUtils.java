package com.keyVault.app.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
	private final static String ACCESS_TOKEN_SECRET = "$10$tTpH1I6caxJcn6uS.zWab.jiWcCQRp5SqklTezw2JUy21w3wBDRo";
	private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
	
	public static String createToken(int id) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
		Date expirationDate = new Date(System.currentTimeMillis()* expirationTime);
		
		Map<String, Object> extra = new HashMap<>();
		extra.put("id", id);
		
		return Jwts.builder()
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
				.compact();
	}
	
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String idString = claims.getSubject();
			int id = Integer.parseInt(idString);
			return new UsernamePasswordAuthenticationToken(id, null, Collections.emptyList());
		} catch (JwtException e) {
			return null;
		}
	}
}
