package com.v1.crello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.v1.crello.util.AppUtil;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrelloApplication {

	private final AppUtil appUtil;

	public static void main(String[] args) {
		SpringApplication.run(CrelloApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {

		appUtil.setAllUserNickname();

		return args -> System.out.println("유저 등록 완료");
	}
}
