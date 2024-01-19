package com.v1.crello.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Update User")
@Builder
public class UpdateUserResponse {

	private String nickname;
	private String photo;
}
