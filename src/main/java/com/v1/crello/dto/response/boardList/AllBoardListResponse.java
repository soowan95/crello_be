package com.v1.crello.dto.response.boardList;

import java.util.List;

import com.v1.crello.card.Card;
import com.v1.crello.dto.response.card.AllCardResponse;

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
	private List<AllCardResponse> cards;
}
