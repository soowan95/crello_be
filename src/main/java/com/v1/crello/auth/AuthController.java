package com.v1.crello.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.v1.crello.dto.request.jwt.RtkRequest;
import com.v1.crello.dto.request.user.LoginRequest;
import com.v1.crello.dto.request.user.LogoutRequest;
import com.v1.crello.dto.response.jwt.RtkResponse;
import com.v1.crello.dto.response.user.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	@Operation(summary = "login", description = "로그인 하여 JWT 발급함.")
	public ResponseEntity<LoginResponse> getLoginToken(@RequestBody LoginRequest loginRequest) {

		return ResponseEntity.ok(authService.getLoginToken(loginRequest.getEmail(), loginRequest.getPassword()));
	}

	@PostMapping("/logout")
	@Operation(summary = "logout", description = "db에 있는 rtk 삭제")
	public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {

		authService.logout(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/api/v1/auth/refresh")
	@Operation(summary = "refresh token", description = "atk 만료 시 rtk으로 atk 재발급")
	public ResponseEntity<RtkResponse> refreshAccessToken(@RequestBody RtkRequest request) {

		return ResponseEntity.ok(authService.refreshAccesToken(request.getRefreshToken(), request.getEmail()));
	}
}
