package com.v1.crello.dto.response.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Recent Worked Board")
@Builder
public class RecentBoardResponse {

	@Schema(description = "가장 최근에 작업한 보드")
	private String title;
	@Schema(description = "보드 배경색")
	private String color;
	private Integer id;
}
