package com.v1.crello.user;

import lombok.Getter;

@Getter
public enum UserRole {
	PREMIUM("Premium"),
	COMMON("Common"),
	TRIAl("Trial");

	private final String label;

	UserRole(String label) {
		this.label = label;
	}
}
