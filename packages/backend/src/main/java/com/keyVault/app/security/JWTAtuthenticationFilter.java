package com.keyVault.app.security;

import java.util.Collections;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyVault.app.dto.TokenDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAtuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public final static String ACCESS_TOKEN_SECRET = "$10$tTpH1I6caxJcn6uS.zWab.jiWcCQRp5SqklTezw2JUy21w3wBDRo";
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		AuthCredentials authCredentials =  new AuthCredentials();
		
		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		}catch (IOException | java.io.IOException e){
		}
		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getEmail(), 
				authCredentials.getPassword(),
				Collections.emptyList()
		);
		return getAuthenticationManager().authenticate(usernamePAT);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws java.io.IOException, ServletException {
		UserDetailsImpl userDetails = (UserDetailsImpl)authResult.getPrincipal();
		int id= userDetails.getUser().getId();
		String token = TokenUtils.createToken(id);
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
		Date expirationToken = claims.getExpiration();
		TokenDTO tokenDTO = new TokenDTO(token, expirationToken);
		System.out.println(tokenDTO);
		//UserResponse userResponse = userService.findUserById(id);
		//response.addHeader("Authorization", "Bearer " + token);
		//response.getWriter().println(userResponse);
		//response.getWriter().write(token);
		//response.getWriter().print(tokenDTO);
		response.getWriter().println(token);
		response.getWriter().flush();
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
