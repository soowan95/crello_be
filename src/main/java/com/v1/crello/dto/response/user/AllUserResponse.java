package com.v1.crello.dto.response.user;

import java.util.List;
import java.util.Map;

import lombok.Getter;

public class AllUserResponse {

	private static List<String> all;

	public static List<String> getAll() {
		return all;
	}

	public static void setAll(List<String> users) {
		all = users;
	}
}
