package com.v1.crello.user;

import java.util.Comparator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.v1.crello.util.AppUtil;
import com.v1.crello.dto.request.user.ChangePasswordRequest;
import com.v1.crello.dto.request.user.RegistUserRequest;
import com.v1.crello.dto.request.user.RoleUpdateRequest;
import com.v1.crello.dto.request.user.UpdateUserRequest;
import com.v1.crello.dto.response.user.AllUserResponse;
import com.v1.crello.dto.response.user.UpdateUserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/")
@Tag(name = "User", description = "User API Document")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final AppUtil appUtil;

	@PostMapping("regist")
	@Operation(summary = "Regist User", description = "유저 정보를 등록함.")
	public ResponseEntity<Void> regist(@RequestBody RegistUserRequest request) {
		userService.regist(request);
		return ResponseEntity.ok().build();
	}

	@GetMapping("check")
	@Operation(summary = "Check User Email", description = "중복된 이메일이 있는지 확인")
	public ResponseEntity<Void> checkUserEmail(@RequestParam String email) {
		userService.checkUserEmail(email);
		return ResponseEntity.ok().build();
	}

	@GetMapping("checkCode/{code}")
	@Operation(summary = "Check User Code", description = "코드가 유효한지 확인")
	public ResponseEntity<Void> checkUserCode(@PathVariable String code) {
		userService.checkUserCode(code);
		return ResponseEntity.ok().build();
	}

	@PutMapping("update")
	@Operation(summary = "Update User", description = "유저 정보 업데이트")
	public ResponseEntity<UpdateUserResponse> update(UpdateUserRequest request,
		@RequestParam(value = "photo", required = false) MultipartFile photo) {
		UpdateUserResponse update = userService.update(request, photo);
		appUtil.setAllUserNickname();
		return ResponseEntity.ok(update);
	}

	@DeleteMapping("delete")
	@Operation(summary = "Delete User", description = "유저 탈퇴")
	public ResponseEntity<Void> delete(@RequestParam String password,
		@RequestParam String email) {
		userService.delete(password, email);

		return ResponseEntity.ok().build();
	}

	@PutMapping("changepw")
	@Operation(summary = "Change Password", description = "비밀번호 변경")
	public ResponseEntity<Void> changepw(@RequestBody ChangePasswordRequest request) {
		userService.changepw(request);

		return ResponseEntity.ok().build();
	}

	@PutMapping("roleUpdate")
	@Operation(summary = "Role Update", description = "유저 등급 변경")
	public ResponseEntity<Void> roleUpdate(@RequestBody RoleUpdateRequest request) {
		userService.roleUpdate(request);

		return ResponseEntity.ok().build();
	}

	@GetMapping("searchAll/{target}")
	@Operation(summary = "Search All User", description = "검색 시 자동완성 시스템")
	public ResponseEntity<List<String>> searchAll(@PathVariable("target") String target) {
		List<String> list = AllUserResponse.getAll().stream().filter(a -> a.contains(target)).sorted(
			Comparator.comparingInt(a -> a.compareTo(target))).limit(10).toList();

		return ResponseEntity.ok(list);
	}
}
