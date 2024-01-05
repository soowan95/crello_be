package com.v1.crello.config.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final TokenProvider tokenProvider;

	@Override
	public void configure(HttpSecurity http) {

		System.out.println("configure");

		JwtFilter custonFilter = new JwtFilter(tokenProvider);
		http.addFilterBefore(custonFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
