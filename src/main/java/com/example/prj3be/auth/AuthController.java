package com.example.prj3be.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.prj3be.config.security.jwt.JwtFilter;
import com.example.prj3be.config.security.jwt.TokenProvider;
import com.example.prj3be.dto.request.LoginRequest;
import com.example.prj3be.dto.response.LoginResponse;
import com.example.prj3be.dto.response.TokenInfo;
import com.example.prj3be.user.User;
import com.example.prj3be.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> getLoginToken(@RequestBody LoginRequest loginRequest) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			loginRequest.getId(), loginRequest.getPassword());

		System.out.println(authenticationToken);

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		TokenInfo jwt = tokenProvider.createToken(authentication);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

		User user = userService.findById(loginRequest.getId());

		return ResponseEntity.ok(LoginResponse.builder()
			.accessToken(jwt.getAccessToken())
			.refreshToken(jwt.getRefreshToken())
			.id(user.getId())
			.name(user.getName())
			.email(user.getEmail())
			.build());
	}
}
