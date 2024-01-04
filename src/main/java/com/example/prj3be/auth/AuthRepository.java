package com.example.prj3be.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prj3be.entity.User;

public interface AuthRepository extends JpaRepository<User, String> {

	User getUserByEmail(String email);
}
