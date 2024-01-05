package com.example.prj3be.board;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prj3be.dto.request.board.CreateBoardRequest;
import com.example.prj3be.dto.request.board.UpdateRecentBoardRequest;
import com.example.prj3be.dto.response.board.AllBoardResponse;
import com.example.prj3be.dto.response.board.RecentBoardResponse;
import com.example.prj3be.entity.Board;
import com.example.prj3be.entity.User;
import com.example.prj3be.exception.CustomEnum;
import com.example.prj3be.exception.CustomException;
import com.example.prj3be.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	public void save(CreateBoardRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_EMAIL));

		boardRepository.save(Board.builder()
			.user(user)
			.title(request.getTitle())
			.updated(LocalDateTime.now())
			.build());
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
				.build());
		}

		return responses;
	}

	public RecentBoardResponse findByUserEmailAndUpdated(String email) {

		Board board = boardRepository.findByUserEmailLimitOne(email);

		return RecentBoardResponse.builder()
			.id(board.getId())
			.title(board.getTitle())
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
			.build());
	}
}
