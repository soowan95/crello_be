package com.v1.crello.boardList;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.crello.entity.BoardList;

public interface BoardListRepository extends JpaRepository<BoardList, Integer> {
	List<BoardList> findByBoardId(Integer boardId);
}
