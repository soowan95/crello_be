package com.v1.crello.board;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v1.crello.dto.request.board.CreateBoardRequest;
import com.v1.crello.dto.request.board.UpdateBoardRequest;
import com.v1.crello.dto.request.board.UpdateRecentBoardRequest;
import com.v1.crello.dto.response.board.AllBoardResponse;
import com.v1.crello.dto.response.board.RecentBoardResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/board")
@Tag(name = "Board", description = "Board API Document")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@PostMapping("/create")
	@Operation(summary = "Create board", description = "특정 유저의 보드를 생성함.")
	public ResponseEntity<Void> create(@RequestBody CreateBoardRequest request) {
		boardService.create(request);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/all")
	@Operation(summary = "Find all board", description = "특정 유저의 모든 보드를 탐색함.")
	public ResponseEntity<List<AllBoardResponse>> findAll(@RequestParam String email) {
		return ResponseEntity.ok(boardService.findAllByUserEmail(email));
	}

	@GetMapping("/recent")
	@Operation(summary = "Find recent worked board", description = "가장 최근에 작업한 보드를 탐색함.")
	public ResponseEntity<RecentBoardResponse> findRecent(@RequestParam String email) {
		return ResponseEntity.ok(boardService.findByUserEmailAndUpdated(email));
	}

	@PutMapping("/updateRecent")
	@Operation(summary = "Update Board", description = "가장 최근에 작업한 시간 업데이트")
	public ResponseEntity<Void> updateBoard(@RequestBody UpdateRecentBoardRequest request) {
		boardService.updateBoard(request);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/update")
	@Operation(summary = "Update Title", description = "해당 보드의 제목 변경")
	public ResponseEntity<Void> updateTitle(@RequestBody UpdateBoardRequest request) {
		boardService.updateBoardTitle(request);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/delete")
	@Operation(summary = "Delete Board", description = "해당 보드 삭제")
	public ResponseEntity<Void> deleteBoard(@RequestParam Integer id) {
		boardService.deleteBoard(id);
		return ResponseEntity.ok().build();
	}
}
