package com.example.prj3be.config.security.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.prj3be.auth.AuthRepository;
import com.example.prj3be.entity.User;

import lombok.RequiredArgsConstructor;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final AuthRepository authRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = authRepository.getUserById(username);
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getId(),
			user.getPassword(),
			AuthorityUtils.createAuthorityList(user.getUserRole().name()));

		System.out.println(userDetails);

		return userDetails;
	}
}
