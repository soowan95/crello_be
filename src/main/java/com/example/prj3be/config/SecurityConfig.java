package com.example.prj3be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.prj3be.config.security.jwt.JwtAccessDeniedHandler;
import com.example.prj3be.config.security.jwt.JwtAuthenticationEntryPoint;
import com.example.prj3be.config.security.jwt.JwtSecurityConfig;
import com.example.prj3be.config.security.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security 활성화
@EnableMethodSecurity // @preAuthorize 어노테이션 메소드 단위로 추가
@RequiredArgsConstructor
public class SecurityConfig {

	private static final String[] PERMIT_URL_ARRAY = {
		"/api-docs/**",
		"/swagger-ui/**",
		"/prj3-ui.html",
		"/",
		"/login"
	};

	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	// 비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring()
			.requestMatchers("/resources/**",
				"/api/v1/user/regist");
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.csrf().disable()
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.accessDeniedHandler(jwtAccessDeniedHandler)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.requestMatchers(PERMIT_URL_ARRAY).permitAll()
			.anyRequest().authenticated()
			.and()
			.apply(new JwtSecurityConfig(tokenProvider));

		return http.build();
	}
}
