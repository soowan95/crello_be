package com.example.prj3be;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.prj3be.board.BoardRepository;
import com.example.prj3be.entity.Board;
import com.example.prj3be.entity.User;
import com.example.prj3be.user.UserRepository;
import com.example.prj3be.user.UserRole;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Prj3BeApplication {

  private final UserRepository userRepository;
  private final BoardRepository boardRepository;
  private final PasswordEncoder bCryptPasswordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(Prj3BeApplication.class, args);
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

    boardRepository.save(Board.builder()
        .user(user)
        .title(user.getNickname() + "'s First Board")
        .build());

    return args -> System.out.println("테스트용 아이디 생성");
  }
}
