package com.example.prj3be.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prj3be.entity.Board;
import com.example.prj3be.entity.User;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	List<Board> findAllByUser(User user);
}
