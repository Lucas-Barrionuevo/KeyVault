package com.keyVault.app.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		String idString= String.valueOf(user.getId());
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
		Map<String, Object> extra = new HashMap<>();
		extra.put("id", idString);
		String token =  Jwts.builder()
				.setSubject(idString)
				.setExpiration(fechaExpiracion)
				.addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
				.compact();
		/*Token jwtToken = new JwtToken();
		// Complete todos los parámetros: algoritmo, emisor, tiempo de vencimiento, otras reclamaciones, etc. // Obtenga la clave privada y firme el token con una clave secreta o una clave privada
		jwtToken.setAlgorithm(JwtToken.SIGN_ALGORITHM.HS256.toString());
		jwtToken.setType(JwtToken.JWT);
		jwtToken.setIssuer("my.oracle.com");
		jwtToken.setPrincipal("john.doe");
	*/	
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
	/*public static void main (String[] args) {
	System.out.println("password " + new BCryptPasswordEncoder().encode("prueba"));
	}*/
}
