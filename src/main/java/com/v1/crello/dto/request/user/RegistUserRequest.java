package com.v1.crello.dto.request.user;

import com.v1.crello.user.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Regist User Request")
public class RegistUserRequest {
	@Schema(description = "유저 이름")
	private String nickname;
	@Schema(description = "비밀번호")
	private String password;
	@Schema(description = "이메일")
	private String email;
	@Schema(description = "사진 url")
	private String photo;
	@Schema(description = "유저 등급")
	private UserRole role;
}
