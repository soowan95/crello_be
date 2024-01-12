package com.v1.crello.dto.request.user;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Update User")
public class UpdateUserRequest {

	private String email;
	private String password;
	private String nickname;
}
