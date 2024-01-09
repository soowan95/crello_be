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
import com.v1.crello.dto.request.user.LogoutRequest;
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

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
			password);

		System.out.println(authenticationToken);

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		TokenInfo jwt = tokenProvider.createToken(authentication);

		User user = this.findUserByEmail(email);

		userRepository.save(User.builder()
			.boards(user.getBoards())
			.email(user.getEmail())
			.password(user.getPassword())
			.userRole(user.getUserRole())
			.nickname(user.getNickname())
			.refreshToken(jwt.getRefreshToken())
			.build());

		return LoginResponse.builder()
			.accessToken(jwt.getAccessToken())
			.refreshToken(jwt.getRefreshToken())
			.nickname(user.getNickname())
			.email(user.getEmail())
			.userRole(user.getUserRole())
			.build();
	}

	public void logout(LogoutRequest request) {

		User user = this.findUserByEmail(request.getEmail());

		userRepository.save(User.builder()
			.boards(user.getBoards())
			.email(user.getEmail())
			.password(user.getPassword())
			.userRole(user.getUserRole())
			.nickname(user.getNickname())
			.build());
	}

	public RtkResponse refreshAccesToken(String refreshToken, String email) {

		User user = this.findUserByEmail(email);

		if (user.getRefreshToken().equals(refreshToken)) {

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

			String accessToken = tokenProvider.refreshToken(userDetails);

			return RtkResponse.builder()
				.accessToken(accessToken)
				.build();
		} else throw new CustomException(CustomEnum.INVALID_EMAIL);
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new CustomException(CustomEnum.UNAUTHORIZED));
	}
}
