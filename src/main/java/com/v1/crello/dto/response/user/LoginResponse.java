package com.v1.crello.dto.response.user;

import com.v1.crello.user.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "User Login Response")
@Builder
public class LoginResponse {
	@Schema(description = "유저 이름")
	private String nickname;
	@Schema(description = "이메일")
	private String email;
	@Schema(description = "유저 사진")
	private String photo;
	@Schema(description = "JWT Access Token")
	private String accessToken;
	@Schema(description = "JWT Refresh Token")
	private String refreshToken;
	@Schema(description = "유저 등급")
	private String role;
	@Schema(description = "닉네임 코드")
	private String code;
}
