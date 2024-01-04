package com.example.prj3be.user;

import lombok.Getter;

@Getter
public enum UserRole {
	ADMIN("관리자"),
	MANAGER("매니저"),
	USER("일반사용자");

	private String label;

	UserRole(String label) {
		this.label = label;
	}
}
