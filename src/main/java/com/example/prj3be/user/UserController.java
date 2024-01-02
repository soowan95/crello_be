package com.example.prj3be.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prj3be.dto.request.LoginUserRequest;
import com.example.prj3be.dto.request.RegistUserRequest;
import com.example.prj3be.dto.response.LoginResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/member/")
@Tag(name = "User", description = "User API Document")
@RequiredArgsConstructor
public class UserController {

	private final UserService memberService;

	@PostMapping("regist")
	public ResponseEntity<Void> registMember(@RequestBody RegistUserRequest request) {
		memberService.registMember(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginUserRequest request) {
		return ResponseEntity.ok(memberService.login(request));
	}
}
