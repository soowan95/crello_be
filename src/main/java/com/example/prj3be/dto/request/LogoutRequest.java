package com.example.prj3be.dto.request;

import lombok.Data;

@Data
public class LogoutRequest {

	private String accessToken;
	private String refreshToken;
}
