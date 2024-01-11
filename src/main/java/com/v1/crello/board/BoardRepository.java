package com.v1.crello.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.crello.board.querydsl.BoardRepositoryCustom;
import com.v1.crello.user.User;

public interface BoardRepository extends JpaRepository<Board, Integer>, BoardRepositoryCustom {
	List<Board> findAllByUser(User user);
}
