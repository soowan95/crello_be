package com.v1.crello.boardList;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.crello.boardList.querydsl.BoardListRepositoryCustom;
import com.v1.crello.entity.BoardList;

public interface BoardListRepository extends JpaRepository<BoardList, Integer>, BoardListRepositoryCustom {
	List<BoardList> findByBoardId(Integer boardId);
}
