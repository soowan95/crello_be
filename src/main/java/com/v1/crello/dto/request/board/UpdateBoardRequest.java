package com.v1.crello.dto.request.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Update Board Title")
public class UpdateBoardRequest {

	private String title;
	private Integer id;
}
