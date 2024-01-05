package com.example.prj3be.dto.request.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Update board")
public class UpdateRecentBoardRequest {

	@Schema(description = "최근 작업한 보드 아이디")
	private Integer id;
}
