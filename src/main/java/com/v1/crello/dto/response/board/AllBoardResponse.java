package com.v1.crello.dto.response.board;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "All Board")
@Builder
public class AllBoardResponse {

	@Schema(description = "보드 제목")
	private String title;
	@Schema(description = "보드 아이디")
	private Integer id;
	@Schema(description = "보드 배경색")
	private String color;
	@Schema(description = "공개 여부")
	private Boolean isPublic;
}
