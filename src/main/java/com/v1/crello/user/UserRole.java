package com.v1.crello.user;

import lombok.Getter;

@Getter
public enum UserRole {
	PREMIUM("PREMIUM"),
	COMMON("COMMON"),
	TRIAl("TRIAL");

	private final String label;

	UserRole(String label) {
		this.label = label;
	}
}
