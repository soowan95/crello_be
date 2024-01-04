package com.example.prj3be.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prj3be.dto.request.RegistUserRequest;
import com.example.prj3be.entity.User;
import com.example.prj3be.exception.CustomEnum;
import com.example.prj3be.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder bCryptPasswordEncoder;

	public void registMember(RegistUserRequest request) {

		if (!request.getEmail().matches(".*\\..*"))
			throw new CustomException(CustomEnum.INVALID_EMAIL);

		if (this.isEmailExist(request.getEmail()))
			throw new CustomException(CustomEnum.DUPLICATE_EMAIL);

		User user = User.builder()
			.nickname(request.getNickname() == null ? request.getEmail().split("@")[0] : request.getNickname())
			.password(request.getPassword())
			.email(request.getEmail())
			.userRole(UserRole.USER)
			.build();

		user.hashPassword(bCryptPasswordEncoder);

		userRepository.save(user);
	}

	private boolean isEmailExist(String email) {
		Optional<User> byEmail = userRepository.findByEmail(email);
		return byEmail.isPresent();
	}
}
