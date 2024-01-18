package com.v1.crello.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.v1.crello.dto.response.user.AllUserResponse;
import com.v1.crello.user.User;
import com.v1.crello.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppUtil {

	private final UserRepository userRepository;

	public void setAllUserNickname() {

		List<User> all = userRepository.findAll();

		List<String> users = new ArrayList<>();

		for (User user : all) {
			String nickname = user.getNickname() + "  #" + user.getCode();
			users.add(nickname);
		}

		AllUserResponse.setAll(users);
	}
}
