package com.example.prj3be.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prj3be.dto.request.LoginUserRequest;
import com.example.prj3be.dto.request.RegistUserRequest;
import com.example.prj3be.dto.response.LoginResponse;
import com.example.prj3be.exception.CustomEnum;
import com.example.prj3be.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {

	private final UserRepository userRepository;

	public void registMember(RegistUserRequest request) {

		userRepository.save(User.builder()
			.id(request.getId())
			.name(request.getName())
			.password(request.getPassword())
			.email(request.getEmail())
			.build());
	}

	public LoginResponse login(LoginUserRequest request) {

		User user = userRepository.findByIdAndPassword(request.getId(), request.getPassword())
			.orElseThrow(() -> new CustomException(
				CustomEnum.UNAUTHORIZED));

		return LoginResponse.builder()
			.id(user.getId())
			.name(user.getName())
			.email(user.getEmail())
			.build();
	}
}
