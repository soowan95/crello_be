package com.example.prj3be.dto.response.boardList;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "All BoardList")
@Builder
public class AllBoardListResponse {

	@Schema(description = "리스트 아이디")
	private Integer id;
	@Schema(description = "리스트 제목")
	private String title;
}
