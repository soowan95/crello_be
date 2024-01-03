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
import com.example.prj3be.dto.request.LogoutRequest;
import com.example.prj3be.dto.response.LoginResponse;
import com.example.prj3be.dto.response.TokenInfo;
import com.example.prj3be.entity.User;
import com.example.prj3be.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> getLoginToken(@RequestBody LoginRequest loginRequest) {

		return ResponseEntity.ok(authService.getLoginToken(loginRequest.getId(), loginRequest.getPassword()));
	}
}
