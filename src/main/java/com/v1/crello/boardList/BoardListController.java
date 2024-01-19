package com.v1.crello.boardList;

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

import com.v1.crello.dto.request.boardList.AddBoardListRequest;
import com.v1.crello.dto.request.boardList.MoveBoardListRequest;
import com.v1.crello.dto.request.boardList.UpdateBoardListRequest;
import com.v1.crello.dto.response.boardList.AllBoardListResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/list")
@Tag(name = "List", description = "BoardList API Document")
@RequiredArgsConstructor
public class BoardListController {

	private final BoardListService boardListService;

	@GetMapping("/all")
	@Operation(summary = "Find All List", description = "해당 보드의 모든 리스트 탐색")
	public ResponseEntity<List<AllBoardListResponse>> findAll(@RequestParam Integer boardId) {
		return ResponseEntity.ok(boardListService.findByBoardId(boardId));
	}

	@PutMapping("/update")
	@Operation(summary = "Update Title", description = "해당 리스트의 제목 변경")
	public ResponseEntity<Void> updateTitle(@RequestBody UpdateBoardListRequest request) {
		boardListService.updateListTitle(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/add")
	@Operation(summary = "Add List", description = "해당 보드에 리스트 추가")
	public ResponseEntity<List<AllBoardListResponse>> add(@RequestBody AddBoardListRequest request) {

		return ResponseEntity.ok(boardListService.add(request));
	}

	@DeleteMapping("/delete")
	@Operation(summary = "Delete List", description = "해당 리스트 삭제")
	public ResponseEntity<Void> delete(@RequestParam Integer id) {
		boardListService.delete(id);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/move")
	@Operation(summary = "Move List", description = "특정 보드에 있는 리스트 다른 보드로 옮기기")
	public ResponseEntity<List<AllBoardListResponse>> move(@RequestBody MoveBoardListRequest request) {
		return ResponseEntity.ok(boardListService.move(request));
	}
}
