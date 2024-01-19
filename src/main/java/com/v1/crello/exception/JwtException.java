package com.v1.crello.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtException extends AuthenticationException {

	public JwtException(String msg) {
		super(msg);
	}
}
