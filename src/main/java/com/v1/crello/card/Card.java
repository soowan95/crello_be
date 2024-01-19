package com.v1.crello.card;

import com.v1.crello.boardList.BoardList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "title")
	private String title;
	@Column(name = "content", length = 2000)
	private String content;
	@Column(name = "idx")
	private Integer index;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "list_id")
	BoardList boardList;
}
