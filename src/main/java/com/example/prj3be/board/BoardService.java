package com.example.prj3be.board;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prj3be.dto.request.CreateBoardRequest;
import com.example.prj3be.dto.response.AllBoardResponse;
import com.example.prj3be.dto.response.RecentBoardResponse;
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
			.updated(LocalDate.now())
			.build());
	}

	public AllBoardResponse findAllByUserEmail(String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_EMAIL));

		List<Board> boards = boardRepository.findAllByUser(user);

		AllBoardResponse response = new AllBoardResponse();

		response.setTitle(boards.stream().map(Board::getTitle).toList());

		return response;
	}

	public RecentBoardResponse findByUserEmailAndUpdated(String email) {
		return null;
	}
}
