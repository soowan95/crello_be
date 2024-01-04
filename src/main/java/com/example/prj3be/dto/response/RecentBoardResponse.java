package com.example.prj3be.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Recent Worked Board")
public class RecentBoardResponse {

	@Schema(description = "가장 최근에 작업한 보드")
	private String title;
}
