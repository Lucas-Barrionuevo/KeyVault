package com.keyVault.app.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private final static String ACCESS_TOKEN_SECRET = "$10$tTpH1I6caxJcn6uS.zWab.jiWcCQRp5SqklTezw2JUy21w3wBDRo";
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String bearerToken = request.getHeader("Authorization");
		
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.replace("Bearer ", "");
			UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(usernamePAT);
		}
		filterChain.doFilter(request, response);
	}

}
