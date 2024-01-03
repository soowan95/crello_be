package com.example.prj3be.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequest {
	@Schema(description = "아이디")
	private String id;
	@Schema(description = "비밀번호")
	private String password;
}
