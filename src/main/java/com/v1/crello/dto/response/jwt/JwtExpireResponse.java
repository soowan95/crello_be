package com.v1.crello.dto.response.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtExpireResponse {

	private String msg;
	private Integer code;
}
