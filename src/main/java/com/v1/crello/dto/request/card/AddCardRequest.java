package com.v1.crello.dto.request.card;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Add Card")
public class AddCardRequest {

	@Schema(description = "리스트 아이디")
	private Integer listId;
	@Schema(description = "보드 아이디")
	private Integer boardId;
	private String title;
	private String content;
}
