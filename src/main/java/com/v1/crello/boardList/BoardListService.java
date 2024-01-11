package com.v1.crello.boardList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v1.crello.board.BoardRepository;
import com.v1.crello.card.Card;
import com.v1.crello.card.CardRepository;
import com.v1.crello.dto.request.boardList.AddBoardListRequest;
import com.v1.crello.dto.request.boardList.MoveBoardListRequest;
import com.v1.crello.dto.request.boardList.UpdateBoardListRequest;
import com.v1.crello.dto.response.boardList.AllBoardListResponse;
import com.v1.crello.board.Board;
import com.v1.crello.dto.response.card.AllCardResponse;
import com.v1.crello.exception.CustomEnum;
import com.v1.crello.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BoardListService {

	private final BoardListRepository boardListRepository;
	private final BoardRepository boardRepository;
	private final CardRepository cardRepository;

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
			List<AllCardResponse> cards = cardRepository.findByBoardList_Id(list.getId())
				.stream()
				.map(a -> AllCardResponse.builder()
					.content(a.getContent())
					.id(a.getId())
					.title(a.getTitle())
					.build())
				.toList();

			responses.add(AllBoardListResponse.builder()
				.id(list.getId())
				.title(list.getTitle())
				.cards(cards)
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
