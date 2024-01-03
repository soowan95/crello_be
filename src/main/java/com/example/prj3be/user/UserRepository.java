package com.example.prj3be.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prj3be.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findById(String id);
	Optional<User> findByEmail(String email);
}
