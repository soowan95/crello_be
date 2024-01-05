package com.example.prj3be.dto.response.board;

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
}
