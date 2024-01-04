package com.example.prj3be.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.prj3be.dto.request.RegistUserRequest;

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
	public ResponseEntity<Void> registMember(@RequestBody RegistUserRequest request) {
		userService.registMember(request);
		return ResponseEntity.ok().build();
	}
}
