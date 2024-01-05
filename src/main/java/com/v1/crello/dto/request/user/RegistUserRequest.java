package com.v1.crello.dto.request.user;

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
}
