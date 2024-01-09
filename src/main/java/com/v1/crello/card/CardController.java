package com.v1.crello.card;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.crello.dto.request.card.AddCardRequest;
import com.v1.crello.dto.response.boardList.AllBoardListResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/card")
@Tag(name = "Card", description = "Card API Document")
@RequiredArgsConstructor
public class CardController {

	private final CardService cardService;

	@PostMapping("/add")
	@Operation(summary = "Add Card", description = "리스트에 카드 추가")
	public ResponseEntity<List<AllBoardListResponse>> add(@RequestBody AddCardRequest request) {
		return ResponseEntity.ok(cardService.add(request));
	}
}
