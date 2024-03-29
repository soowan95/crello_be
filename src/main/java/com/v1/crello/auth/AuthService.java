package com.v1.crello.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v1.crello.config.security.jwt.CustomUserDetailsService;
import com.v1.crello.config.security.jwt.TokenProvider;
import com.v1.crello.dto.request.user.LogoutRequest;
import com.v1.crello.dto.response.jwt.RtkResponse;
import com.v1.crello.dto.response.user.LoginResponse;
import com.v1.crello.dto.response.user.TokenInfo;
import com.v1.crello.user.User;
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

	@Value("${image.file.prefix}")
	private String urlPrefix;

	public LoginResponse getLoginToken(String email, String password) {

		User user = this.findUserByEmail(email);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
			password);

		System.out.println(authenticationToken);

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		TokenInfo jwt = tokenProvider.createToken(authentication);

		userRepository.save(User.builder()
			.boards(user.getBoards())
			.email(user.getEmail())
			.password(user.getPassword())
			.userRole(user.getUserRole())
			.nickname(user.getNickname())
			.photo(user.getPhoto())
			.code(user.getCode())
			.refreshToken(jwt.getRefreshToken())
			.build());

		String photoUrl;
		if (user.getPhoto() == null)
			photoUrl = null;
		else if (!user.getPhoto().startsWith("http"))
			photoUrl = urlPrefix + "crello/user/" + user.getEmail() + "/" + user.getPhoto();
		else
			photoUrl = user.getPhoto();

		return LoginResponse.builder()
			.accessToken(jwt.getAccessToken())
			.refreshToken(jwt.getRefreshToken())
			.nickname(user.getNickname())
			.email(user.getEmail())
			.photo(photoUrl)
			.role(user.getUserRole().getLabel())
			.code(user.getCode())
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
			.photo(user.getPhoto())
			.code(user.getCode())
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
		} else
			throw new CustomException(CustomEnum.INVALID_EMAIL);
	}

	public LoginResponse oauthLogin(String email) {
		User user = this.findUserByEmail(email);

		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

		TokenInfo jwt = tokenProvider.oauthToken(userDetails);

		String photoUrl;
		if (user.getPhoto() == null)
			photoUrl = null;
		else if (!user.getPhoto().startsWith("http"))
			photoUrl = urlPrefix + "crello/user/" + user.getEmail() + "/" + user.getPhoto();
		else
			photoUrl = user.getPhoto();

		return LoginResponse.builder()
			.accessToken(jwt.getAccessToken())
			.refreshToken(jwt.getRefreshToken())
			.nickname(user.getNickname())
			.email(user.getEmail())
			.photo(photoUrl)
			.role(user.getUserRole().getLabel())
			.code(user.getCode())
			.build();
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_EMAIL));
	}
}
