package com.example.prj3be.user;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prj3be.board.BoardRepository;
import com.example.prj3be.boardList.BoardListRepository;
import com.example.prj3be.boardList.BoardListService;
import com.example.prj3be.dto.request.user.RegistUserRequest;
import com.example.prj3be.entity.Board;
import com.example.prj3be.entity.BoardList;
import com.example.prj3be.entity.User;
import com.example.prj3be.exception.CustomEnum;
import com.example.prj3be.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {

	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final BoardListService boardListService;
	private final PasswordEncoder bCryptPasswordEncoder;

	public void registUser(RegistUserRequest request) {

		if (!request.getEmail().matches(".*\\..*"))
			throw new CustomException(CustomEnum.INVALID_EMAIL);

		User user = User.builder()
			.nickname(request.getNickname() == null ? request.getEmail().split("@")[0] : request.getNickname())
			.password(request.getPassword())
			.email(request.getEmail())
			.userRole(UserRole.USER)
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
	}

	public void checkUserEmail(String email) {
		if (this.isEmailExist(email))
			throw new CustomException(CustomEnum.DUPLICATE_EMAIL);
	}

	private boolean isEmailExist(String email) {
		Optional<User> byEmail = userRepository.findByEmail(email);
		return byEmail.isPresent();
	}
}
