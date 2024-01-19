package com.v1.crello.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User Login Request")
public class LoginRequest {
	@Schema(description = "이메일")
	private String email;
	@Schema(description = "비밀번호")
	private String password;
}
