package com.v1.crello.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	private final HttpStatus status;
	private final String msg;
	public CustomException(CustomEnum customEnum) {
		this.status = customEnum.getStatus();
		this.msg = customEnum.getMsg();
	}
}
