package com.example.prj3be.boardList;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prj3be.entity.BoardList;

public interface BoardListRepository extends JpaRepository<BoardList, Integer> {
	List<BoardList> findByBoardId(Integer boardId);
}
