package com.v1.crello.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v1.crello.config.security.jwt.CustomUserDetailsService;
import com.v1.crello.config.security.jwt.JwtFilter;
import com.v1.crello.config.security.jwt.TokenProvider;
import com.v1.crello.dto.response.jwt.RtkResponse;
import com.v1.crello.dto.response.user.LoginResponse;
import com.v1.crello.dto.response.user.TokenInfo;
import com.v1.crello.entity.User;
import com.v1.crello.exception.CustomEnum;
import com.v1.crello.exception.CustomException;
import com.v1.crello.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AuthService {

	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final UserRepository userRepository;
	private final CustomUserDetailsService customUserDetailsService;


	public LoginResponse getLoginToken(String email, String password) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

		System.out.println(authenticationToken);

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		TokenInfo jwt = tokenProvider.createToken(authentication);

		User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomException(CustomEnum.INVALID_EMAIL));

		return LoginResponse.builder()
			.accessToken(jwt.getAccessToken())
			.refreshToken(jwt.getRefreshToken())
			.nickname(user.getNickname())
			.email(user.getEmail())
			.userRole(user.getUserRole())
			.build();
	}

	public RtkResponse refreshAccesToken(String refreshToken, String email) {

		UserDetails user = customUserDetailsService.loadUserByUsername(email);

		String accessToken = tokenProvider.refreshToken(user);

		return RtkResponse.builder()
			.accessToken(accessToken)
			.build();
	}
}
