package com.keyVault.app.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.keyVault.app.entity.User;
import com.keyVault.app.exceptions.KeyVaultAppException;
import com.keyVault.app.exceptions.ResourceNotFoundException;
import com.keyVault.app.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	@Autowired
	private UserRepository userRepository;
	public String generarToken(Authentication authentication) {
		String email = authentication.getName();
		User user = userRepository.findOneByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", 0));
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
		Map<String, Object> extra = new HashMap<>();
		extra.put("email", email);
		String token =  Jwts.builder()
				.setSubject(email)
				.setExpiration(fechaExpiracion)
				.addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
				.compact();
		return token;
	}
	
	public String obtenerUsernameDelJWT(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(jwtSecret.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(token);
			return true;
		}catch (SignatureException ex) {
			throw new KeyVaultAppException(HttpStatus.BAD_REQUEST,"Firma JWT no valida");
		}
		catch (MalformedJwtException ex) {
			throw new KeyVaultAppException(HttpStatus.BAD_REQUEST,"Token JWT no valida");
		}
		catch (ExpiredJwtException ex) {
			throw new KeyVaultAppException(HttpStatus.BAD_REQUEST,"Token JWT caducado");
		}
		catch (UnsupportedJwtException ex) {
			throw new KeyVaultAppException(HttpStatus.BAD_REQUEST,"Token JWT no compatible");
		}
		catch (IllegalArgumentException ex) {
			throw new KeyVaultAppException(HttpStatus.BAD_REQUEST,"La cadena claims JWT esta vacia");
		}
	}
}
