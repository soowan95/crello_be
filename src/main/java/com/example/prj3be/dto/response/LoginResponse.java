package com.example.prj3be.dto.response;

import com.example.prj3be.user.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {
	@Schema(description = "아이디")
	private String id;
	@Schema(description = "유저 이름")
	private String name;
	@Schema(description = "이메일")
	private String email;
	@Schema(description = "JWT Access Token")
	private String accessToken;
	@Schema(description = "JWT Refresh Token")
	private String refreshToken;
	@Schema(description = "권한")
	private UserRole userRole;

	@Builder
	public LoginResponse(String id, String name, String email, String accessToken, String refreshToken, UserRole userRole) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.userRole = userRole;
	}
}
