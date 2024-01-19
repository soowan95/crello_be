package com.v1.crello.dto.response.jwt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RtkResponse {

	private String accessToken;
}
