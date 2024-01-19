package com.v1.crello.dto.request.boardList;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Add List")
public class AddBoardListRequest {

	private Integer boardId;
	private String title;
}
