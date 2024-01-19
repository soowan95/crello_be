package com.v1.crello.board.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.v1.crello.board.Board;
import com.v1.crello.board.QBoard;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Board findByUserEmailLimitOne(String email) {
		QBoard board = QBoard.board;
		return jpaQueryFactory.selectFrom(board)
			.where(board.user.email.eq(email))
			.orderBy(board.updated.desc())
			.limit(1)
			.fetchOne();
	}
}
