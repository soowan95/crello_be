package com.v1.crello.board;

import java.time.LocalDateTime;
import java.util.List;

import com.v1.crello.boardList.BoardList;
import com.v1.crello.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "title")
	private String title;
	@Column(name = "updated")
	private LocalDateTime updated;
	@Column(name = "color")
	private String color;
	@Column(name = "isPublic")
	private Boolean isPublic;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_email")
	private User user;
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<BoardList> boardLists;
}
