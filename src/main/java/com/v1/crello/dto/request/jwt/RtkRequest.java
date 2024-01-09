package com.v1.crello.dto.request.jwt;

import lombok.Data;

@Data
public class RtkRequest {

	private String email;
	private String refreshToken;
}
