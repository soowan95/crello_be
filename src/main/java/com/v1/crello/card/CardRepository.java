package com.v1.crello.card;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
	List<Card> findByBoardList_Id(Integer id);
}
