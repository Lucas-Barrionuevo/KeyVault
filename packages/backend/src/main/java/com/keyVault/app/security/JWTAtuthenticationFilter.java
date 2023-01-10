package com.keyVault.app.security;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.keyVault.app.dto.TokenDTO;
import com.keyVault.app.dto.UserDTO;
import com.keyVault.app.dto.UserResponse;
import com.keyVault.app.repository.UserRepository;
import com.keyVault.app.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JWTAtuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public final static String ACCESS_TOKEN_SECRET = "$10$tTpH1I6caxJcn6uS.zWab.jiWcCQRp5SqklTezw2JUy21w3wBDRo";
	@Autowired
	private UserService userService;
	
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
		Gson myGson = new Gson();
		JsonObject tokenObj = new JsonObject();
		tokenObj.addProperty("token", token);
		tokenObj.addProperty("expirationDate", expirationToken.toString());
		
		JsonObject userObj = new JsonObject();
		userObj.addProperty("user", myGson.toJson(userDetails));
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		userObj.add("token", tokenObj);
		String res = myGson.toJson(userObj);
		out.print(res);
		out.flush();
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
