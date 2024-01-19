package com.v1.crello.boardList;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardListRepository extends JpaRepository<BoardList, Integer> {
	List<BoardList> findByBoardId(Integer boardId);
}
