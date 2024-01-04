package com.example.prj3be.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "All Board")
public class AllBoardResponse {

	@Schema(description = "보드 제목")
	private List<String> title;
}
