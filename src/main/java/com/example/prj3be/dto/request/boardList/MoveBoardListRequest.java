package com.example.prj3be.dto.request.boardList;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Move List")
public class MoveBoardListRequest {

	private Integer prevId;
	private Integer nextId;
	private Integer id;
}
