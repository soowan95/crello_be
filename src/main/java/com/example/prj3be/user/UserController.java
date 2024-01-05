package com.example.prj3be.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.prj3be.dto.request.user.RegistUserRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/")
@Tag(name = "User", description = "User API Document")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("regist")
	@Operation(summary = "regist user", description = "유저 정보를 등록함.")
	public ResponseEntity<Void> registUser(@RequestBody RegistUserRequest request) {
		userService.registUser(request);
		return ResponseEntity.ok().build();
	}

	@GetMapping("check")
	@Operation(summary = "check user email", description = "중복된 이메일이 있는지 확인")
	public ResponseEntity<Void> checkUserEmail(@RequestParam String email) {
		userService.checkUserEmail(email);
		return ResponseEntity.ok().build();
	}
}
