package com.v1.crello.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.v1.crello.config.security.jwt.JwtAuthenticationEntryPoint;
import com.v1.crello.config.security.jwt.JwtFilter;
import com.v1.crello.user.UserRole;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security 활성화
@EnableMethodSecurity // @preAuthorize 어노테이션 메소드 단위로 추가
@RequiredArgsConstructor
public class SecurityConfig {

	private static final String[] PERMIT_URL_ARRAY = {
		"/api-docs/**",
		"/swagger-ui/**",
		"/crello-ui.html",
		"/api/v1/user/regist",
		"/api/v1/user/check",
		"/api/v1/user/changepw",
		"/api/v1/iamport/verify/**",
		"/",
		"/api/v1/auth/login",
		"/api/v1/auth/oauthLogin",
		"/api/v1/auth/logout",
	};

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtFilter jwtFilter;

	// 비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// cors 설정
	CorsConfigurationSource corsConfigurationSource() {
		return request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedHeaders(Collections.singletonList("*"));
			config.setAllowedMethods(Collections.singletonList("*"));

			config.setAllowedOriginPatterns(Collections.singletonList("http://43.200.39.112"));
			config.setAllowCredentials(true);

			return config;
		};
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.logout((logoutConfig) -> logoutConfig.logoutUrl("logout").logoutSuccessUrl("/"))
			.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
			.csrf(AbstractHttpConfigurer::disable)
			.exceptionHandling(
				(exceptionConfig) -> exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			.sessionManagement((sessionConfig) -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
				.requestMatchers(PERMIT_URL_ARRAY).permitAll()
				.anyRequest().authenticated());

		return http.build();
	}
}
