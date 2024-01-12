package com.v1.crello.user;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.v1.crello.board.Board;
import com.v1.crello.board.BoardRepository;
import com.v1.crello.boardList.BoardListService;
import com.v1.crello.dto.request.user.ChangePasswordRequest;
import com.v1.crello.dto.request.user.RegistUserRequest;
import com.v1.crello.dto.request.user.UpdateUserRequest;
import com.v1.crello.dto.response.user.UpdateUserResponse;
import com.v1.crello.exception.CustomEnum;
import com.v1.crello.exception.CustomException;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {

	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final BoardListService boardListService;
	private final PasswordEncoder bCryptPasswordEncoder;
	private final S3Client s3;

	@Value("${image.file.prefix}")
	private String urlPrefix;

	@Value("${aws.s3.bucket.name}")
	private String bucket;

	public void regist(RegistUserRequest request) {

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
			.color("#1d285d")
			.build();

		boardRepository.save(board);

		boardListService.initialCreate(board);
	}

	public void checkUserEmail(String email) {
		if (this.isEmailExist(email))
			throw new CustomException(CustomEnum.DUPLICATE_EMAIL);
	}

	private boolean isEmailExist(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	public UpdateUserResponse update(UpdateUserRequest request, MultipartFile photo) {
		User user = userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new CustomException(CustomEnum.UNAUTHORIZED));

		if (user.getPhoto() != null && photo != null)
			deleteOnS3(user.getEmail(), user.getPhoto());

		User updateUser = User.builder()
			.email(user.getEmail())
			.userRole(user.getUserRole())
			.boards(user.getBoards())
			.refreshToken(user.getRefreshToken())
			.password(request.getPassword() == null ? user.getPassword() : request.getPassword())
			.nickname(request.getNickname() == null ? user.getNickname() : request.getNickname())
			.photo(photo == null ? user.getPhoto() : photo.getOriginalFilename())
			.build();

		if (request.getPassword() != null)
			updateUser.hashPassword(bCryptPasswordEncoder);

		userRepository.save(updateUser);

		String photoUrl = null;

		if (photo != null) {
			this.uploadOnS3(request.getEmail(), photo);
			photoUrl = urlPrefix + "crello/user/" + user.getEmail() + "/" + user.getPhoto();
		}

		return UpdateUserResponse.builder()
			.nickname(request.getNickname())
			.photo(photoUrl)
			.build();
	}

	public void changepw(ChangePasswordRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new CustomException(CustomEnum.UNAUTHORIZED));

		if (user.checkPassword(request.getPassword(), bCryptPasswordEncoder)) throw new CustomException(CustomEnum.SAME_PASSWORD);

		User updateUser = User.builder()
			.email(user.getEmail())
			.userRole(user.getUserRole())
			.boards(user.getBoards())
			.password(request.getPassword())
			.nickname(user.getNickname())
			.photo(user.getPhoto())
			.build();

		updateUser.hashPassword(bCryptPasswordEncoder);

		userRepository.save(updateUser);
	}

	public void delete(String password, String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new CustomException(CustomEnum.INVALID_EMAIL));

		if (user.checkPassword(password, bCryptPasswordEncoder))
			userRepository.delete(user);
		else
			throw new CustomException(CustomEnum.FORBIDDEN);
	}

	public void uploadOnS3(String email, MultipartFile file) {
		String key = "crello/user/" + email + "/" + file.getOriginalFilename();

		PutObjectRequest objectRequest = PutObjectRequest.builder()
			.bucket(bucket)
			.key(key)
			.acl(ObjectCannedACL.PUBLIC_READ)
			.build();

		try {
			s3.putObject(objectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
		} catch (IOException e) {
			throw new CustomException(CustomEnum.IOEXCEPTION);
		}
	}

	public void deleteOnS3(String email, String photo) {
		String key = "crello/user/" + email + "/" + photo;

		DeleteObjectRequest objectRequest = DeleteObjectRequest.builder()
			.bucket(bucket)
			.key(key)
			.build();

		s3.deleteObject(objectRequest);
	}
}
