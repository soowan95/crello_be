package com.example.prj3be.board.querydsl;

import com.example.prj3be.entity.Board;
import com.example.prj3be.entity.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;

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
