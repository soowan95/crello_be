package com.v1.crello.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomEnum {
	NON_AUTHENTICATE(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
	FORBIDDEN(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다."),
	DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 사용중인 이메일입니다."),
	INVALID_EMAIL(HttpStatus.BAD_REQUEST, "유효하지 않은 이메일입니다."),
	INVALID_CODE(HttpStatus.BAD_REQUEST, "유효하지 않은 유저 코드입니다."),
	INVALID_BOARD_ID(HttpStatus.BAD_REQUEST, "일치하는 보드 정보가 없습니다."),
	INVALID_LIST_ID(HttpStatus.BAD_REQUEST, "일치하는 리스트 정보가 없습니다."),
	INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 사용자 정보입니다."),
	INVALID_CARD_ID(HttpStatus.BAD_REQUEST, "일치하는 카드 정보가 없습니다."),
	IOEXCEPTION(HttpStatus.BAD_REQUEST, "파일을 읽는 중 문제가 발생했습니다."),
	SAME_PASSWORD(HttpStatus.BAD_REQUEST, "기존 비밀번호와 일치합니다."),
	;
	private final HttpStatus status;
	private final String msg;
}
