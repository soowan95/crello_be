package com.example.prj3be.boardList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prj3be.board.BoardRepository;
import com.example.prj3be.dto.request.boardList.AddBoardListRequest;
import com.example.prj3be.dto.request.boardList.MoveBoardListRequest;
import com.example.prj3be.dto.request.boardList.UpdateBoardListRequest;
import com.example.prj3be.dto.response.boardList.AllBoardListResponse;
import com.example.prj3be.entity.Board;
import com.example.prj3be.entity.BoardList;
import com.example.prj3be.exception.CustomEnum;
import com.example.prj3be.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BoardListService {

	private final BoardListRepository boardListRepository;
	private final BoardRepository boardRepository;

	public void initialCreate(Board board) {
		String[] listTitles = new String[] {"To do", "Doing", "Done"};

		for (String title : listTitles) {
			boardListRepository.save(BoardList.builder()
				.board(board)
				.title(title)
				.build());
		}
	}

	public List<AllBoardListResponse> findByBoardId(Integer boardId) {
		List<BoardList> lists = boardListRepository.findByBoardId(boardId);
		List<AllBoardListResponse> responses = new ArrayList<>();

		for (BoardList list : lists) {
			responses.add(AllBoardListResponse.builder()
				.id(list.getId())
				.title(list.getTitle())
				.build());
		}

		return responses;
	}

	public void updateListTitle(UpdateBoardListRequest request) {
		BoardList list = boardListRepository.findById(request.getId())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_BOARD_ID));

		boardListRepository.save(BoardList.builder()
			.id(list.getId())
			.title(request.getTitle())
			.board(list.getBoard())
			.build());
	}

	public List<AllBoardListResponse> add(AddBoardListRequest request) {
		Board board = boardRepository.findById(request.getBoardId())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_BOARD_ID));

		boardListRepository.save(BoardList.builder()
			.board(board)
			.title(request.getTitle())
			.build());

		return this.findByBoardId(request.getBoardId());
	}

	public void delete(Integer id) {
		boardListRepository.deleteById(id);
	}

	public List<AllBoardListResponse> move(MoveBoardListRequest request) {
		BoardList list = boardListRepository.findById(request.getId())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_LIST_ID));

		Board board = boardRepository.findById(request.getNextId())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_BOARD_ID));

		boardListRepository.save(BoardList.builder()
			.title(list.getTitle())
			.id(list.getId())
			.board(board)
			.build());

		return this.findByBoardId(request.getPrevId());
	}
}
