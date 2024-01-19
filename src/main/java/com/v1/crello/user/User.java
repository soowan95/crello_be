package com.v1.crello.user;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.v1.crello.board.Board;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "password")
	private String password;
	@Column(name = "rtk")
	private String refreshToken;
	@Column(name = "photo")
	private String photo;
	@Column(name = "code")
	private String code;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Board> boards;

	public User hashPassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
		return this;
	}

	public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(plainPassword, this.password);
	}
}
