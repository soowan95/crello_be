package com.v1.crello.dto.request.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Create Board Request")
public class CreateBoardRequest {

	@Schema(description = "보드 제목")
	private String title;
	@Schema(description = "유저 이메일")
	private String email;
	@Schema(description = "보드 배경색")
	private String color;
	@Schema(description = "공개 여부")
	private Boolean isPublic;
}
