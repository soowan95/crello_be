package com.example.prj3be.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomEnum {
	NON_AUTHENTICATE(HttpStatus.FORBIDDEN, "로그인 정보가 일치하지 않습니다."),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다."),
	DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 사용중인 이메일입니다."),
	INVALID_EMAIL(HttpStatus.BAD_REQUEST, "유효하지 않은 이메일입니다."),
	;
	private final HttpStatus status;
	private final String msg;
}
