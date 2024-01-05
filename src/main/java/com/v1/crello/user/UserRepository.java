package com.v1.crello.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.crello.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmail(String email);
}
