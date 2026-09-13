package com.tcc.faltaoque.service;

import com.tcc.faltaoque.entity.User;
import com.tcc.faltaoque.repository.UserRepository;
import com.tcc.faltaoque.mapper.UserMapper;
import com.tcc.faltaoque.dto.request.UserRequest;
import com.tcc.faltaoque.dto.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserResponse createUser(UserRequest userRequest) {
		User user = UserMapper.toRequest(userRequest);
		user.setPassword(passwordEncoder.encode(userRequest.password()));
		userRepository.save(user);
		return UserMapper.toEntity(user);
	}

}
