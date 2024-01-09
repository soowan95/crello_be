package com.v1.crello.boardList.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.v1.crello.entity.QBoardList;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardListRepositoryCustomImpl implements BoardListRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public void deleteByBoardId(Integer id) {
		QBoardList boardList = QBoardList.boardList;
		jpaQueryFactory.delete(boardList).where(boardList.board.id.eq(id)).execute();
	}
}
