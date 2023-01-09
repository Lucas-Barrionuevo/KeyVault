package com.keyVault.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig  {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChan (HttpSecurity http, AuthenticationManager authManager) throws Exception {
		JwtAuthenticationFilter jwtAuthenticationFilter= new JwtAuthenticationFilter();
		//jwtAuthenticationFilter.setAuthenticationManager(authManager);
		//jwtAuthenticationFilter.setFilterProcessesUrl("/user/login");
		/*http.csrf().disable()
		    .exceptionHandling()
		    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
		    .and()
		    .sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and()
		    .authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").permitAll()
		    .antMatchers("/api/auth/**").permitAll()
		    .anyRequest()
		    .authenticated();
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);*/
		return http
				.csrf().disable()
				.exceptionHandling()
			    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
			    .and()
			    .sessionManagement()
			    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			    .and()
			    .authorizeRequests().requestMatchers(HttpMethod.POST, "/user/register").permitAll()
			    .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
			    .anyRequest()
			    .authenticated()
			    .and()
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}
