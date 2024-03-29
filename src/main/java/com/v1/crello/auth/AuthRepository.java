package com.v1.crello.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.crello.user.User;

public interface AuthRepository extends JpaRepository<User, String> {

	User getUserByEmail(String email);
}
