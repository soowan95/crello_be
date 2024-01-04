package com.example.prj3be.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Create Board Request")
public class CreateBoardRequest {

	@Schema(description = "보드 제목")
	private String title;
	@Schema(description = "유저 이메일")
	private String email;
}
