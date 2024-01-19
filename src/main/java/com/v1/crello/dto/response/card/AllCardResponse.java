package com.v1.crello.dto.response.card;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllCardResponse {

	private Integer id;
	private String title;
	private String content;
}
