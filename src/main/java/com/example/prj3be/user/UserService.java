package com.example.prj3be.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prj3be.dto.request.RegistUserRequest;

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
}
