package com.example.prj3be.dto.response.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Recent Worked Board")
@Builder
public class RecentBoardResponse {

	@Schema(description = "가장 최근에 작업한 보드")
	private String title;
	private Integer id;
}
