package com.example.prj3be.board.querydsl;

import com.example.prj3be.entity.Board;

public interface BoardRepositoryCustom {
	Board findByUserEmailLimitOne(String email);
}
