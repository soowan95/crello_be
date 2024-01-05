package com.example.prj3be.dto.response.user;

import com.example.prj3be.user.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "User Login Response")
public class LoginResponse {
	@Schema(description = "유저 이름")
	private String nickname;
	@Schema(description = "이메일")
	private String email;
	@Schema(description = "JWT Access Token")
	private String accessToken;
	@Schema(description = "JWT Refresh Token")
	private String refreshToken;
	@Schema(description = "권한")
	private UserRole userRole;

	@Builder
	public LoginResponse(String nickname, String email, String accessToken, String refreshToken, UserRole userRole) {
		this.nickname = nickname;
		this.email = email;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.userRole = userRole;
	}
}
