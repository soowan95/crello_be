package com.v1.crello.board.querydsl;

import com.v1.crello.board.Board;

public interface BoardRepositoryCustom {
	Board findByUserEmailLimitOne(String email);
}
