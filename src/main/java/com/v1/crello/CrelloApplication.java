package com.v1.crello;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.v1.crello.board.BoardRepository;
import com.v1.crello.boardList.BoardListService;
import com.v1.crello.entity.Board;
import com.v1.crello.entity.User;
import com.v1.crello.user.UserRepository;
import com.v1.crello.user.UserRole;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrelloApplication {

	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final BoardListService boardListService;
	private final PasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CrelloApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		User user = User.builder()
			.email("a")
			.userRole(UserRole.USER)
			.nickname("a")
			.password("a")
			.build();

		user.hashPassword(bCryptPasswordEncoder);

		userRepository.save(user);

		Board board = Board.builder()
			.user(user)
			.title(user.getNickname() + "'s First Board")
			.updated(LocalDateTime.now())
			.build();

		boardRepository.save(board);

		boardListService.initialCreate(board);

		return args -> System.out.println("테스트용 아이디 생성");
	}
}
