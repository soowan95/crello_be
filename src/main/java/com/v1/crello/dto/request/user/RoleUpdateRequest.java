package com.v1.crello.dto.request.user;

import com.v1.crello.user.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User Role Update")
public class RoleUpdateRequest {

	private String email;
	private UserRole role;
}
