package com.v1.crello.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v1.crello.boardList.BoardListRepository;
import com.v1.crello.boardList.BoardListService;
import com.v1.crello.dto.request.board.CreateBoardRequest;
import com.v1.crello.dto.request.board.UpdateBoardRequest;
import com.v1.crello.dto.request.board.UpdateRecentBoardRequest;
import com.v1.crello.dto.response.board.AllBoardResponse;
import com.v1.crello.dto.response.board.RecentBoardResponse;
import com.v1.crello.user.User;
import com.v1.crello.exception.CustomEnum;
import com.v1.crello.exception.CustomException;
import com.v1.crello.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	private final BoardListService boardListService;
	private final BoardListRepository boardListRepository;

	public void create(CreateBoardRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_EMAIL));

		Board board = Board.builder()
			.user(user)
			.title(request.getTitle())
			.updated(LocalDateTime.now())
			.color(request.getColor())
			.build();

		boardRepository.save(board);

		boardListService.initialCreate(board);
	}

	public List<AllBoardResponse> findAllByUserEmail(String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_EMAIL));

		List<Board> boards = boardRepository.findAllByUser(user);
		List<AllBoardResponse> responses = new ArrayList<>();

		for (Board board : boards) {
			responses.add(AllBoardResponse.builder()
				.id(board.getId())
				.title(board.getTitle())
				.color(board.getColor())
				.build());
		}

		return responses;
	}

	public RecentBoardResponse findByUserEmailAndUpdated(String email) {

		Board board = boardRepository.findByUserEmailLimitOne(email);

		if (board == null) return null;

		return RecentBoardResponse.builder()
			.id(board.getId())
			.title(board.getTitle())
			.color(board.getColor())
			.build();
	}

	public void updateBoard(UpdateRecentBoardRequest request) {
		Board board = boardRepository.findById(request.getId())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_BOARD_ID));

		boardRepository.save(Board.builder()
			.id(board.getId())
			.updated(LocalDateTime.now())
			.title(board.getTitle())
			.user(board.getUser())
			.color(board.getColor())
			.build());
	}

	public void updateBoardTitle(UpdateBoardRequest request) {
		Board board = boardRepository.findById(request.getId())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_BOARD_ID));

		boardRepository.save(Board.builder()
			.id(board.getId())
			.updated(LocalDateTime.now())
			.title(request.getTitle())
			.user(board.getUser())
			.color(board.getColor())
			.build());
	}

	public void deleteBoard(Integer id) {
		boardListRepository.deleteByBoardId(id);

		boardRepository.deleteById(id);
	}
}
