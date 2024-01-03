package com.example.prj3be.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class TokenInfo {

	@Schema(description = "JWT 인증 타입")
	private String grantType;
	@Schema(description = "access token")
	private String accessToken;
	@Schema(description = "refresh token")
	private String refreshToken;

	@Builder
	public TokenInfo(String grantType, String accessToken, String refreshToken) {
		this.grantType = grantType;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
