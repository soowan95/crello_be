package com.v1.crello.card;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v1.crello.boardList.BoardList;
import com.v1.crello.boardList.BoardListRepository;
import com.v1.crello.boardList.BoardListService;
import com.v1.crello.dto.request.card.AddCardRequest;
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
}
