package com.v1.crello.dto.request.card;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Move Card")
public class MoveCardRequest {

	private Integer cardId;
	private Integer nextListId;
	private Integer prevListId;
	private Integer boardId;
}
