package com.v1.crello.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Change Password")
public class ChangePasswordRequest {

	private String email;
	private String password;
}
