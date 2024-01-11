package com.v1.crello.dto.request.card;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Update Card Request")
public class UpdateCardRequest {

	@Schema(description = "카드 아이디")
	private Integer id;
	@Schema(description = "보드 아이디")
	private Integer boardId;
	private String title;
	private String content;
}
