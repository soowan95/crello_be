package com.v1.crello.card;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v1.crello.boardList.BoardList;
import com.v1.crello.boardList.BoardListRepository;
import com.v1.crello.boardList.BoardListService;
import com.v1.crello.dto.request.card.AddCardRequest;
import com.v1.crello.dto.request.card.MoveCardRequest;
import com.v1.crello.dto.request.card.UpdateCardRequest;
import com.v1.crello.dto.response.boardList.AllBoardListResponse;
import com.v1.crello.exception.CustomEnum;
import com.v1.crello.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CardService {

	private final CardRepository cardRepository;
	private final BoardListRepository boardListRepository;
	private final BoardListService boardListService;

	public List<AllBoardListResponse> add(AddCardRequest request) {
		BoardList list = boardListRepository.findById(request.getListId())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_LIST_ID));

		cardRepository.save(Card.builder()
			.boardList(list)
			.content(request.getContent())
			.title(request.getTitle())
			.build());

		return boardListService.findByBoardId(request.getBoardId());
	}

	public List<AllBoardListResponse> update(UpdateCardRequest request) {
		Card card = cardRepository.findById(request.getId())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_CARD_ID));

		cardRepository.save(Card.builder()
			.id(card.getId())
			.boardList(card.getBoardList())
			.content(request.getContent())
			.title(request.getTitle())
			.build());

		return boardListService.findByBoardId(request.getBoardId());
	}

	public List<AllBoardListResponse> move(MoveCardRequest request) {
		Card card = cardRepository.findById(request.getCardId())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_CARD_ID));

		BoardList list = boardListRepository.findById(request.getNextListId())
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_LIST_ID));

		cardRepository.save(Card.builder()
			.id(card.getId())
			.boardList(list)
			.content(card.getContent())
			.title(card.getTitle())
			.build());

		return boardListService.findByBoardId(request.getBoardId());
	}
}
