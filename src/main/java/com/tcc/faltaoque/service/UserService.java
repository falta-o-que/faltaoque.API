package com.tcc.faltaoque.service;

import com.tcc.faltaoque.entity.User;
import com.tcc.faltaoque.repository.UserRepository;
import com.tcc.faltaoque.mapper.UserMapper;
import com.tcc.faltaoque.mapper.PantryMapper;
import com.tcc.faltaoque.dto.response.UserResponse;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.exception.UserNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public List<UserResponse> listAllUsers() {
		return userRepository.findAll()
			.stream()
			.map(UserMapper::toEntity)
			.collect(Collectors.toList());
	}

	public UserResponse findUserById(String id) {
		return UserMapper.toEntity(userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id)));
	}

	public List<PantryResponse> listAllPantriesByUser(String id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));

		return user.getPantries()
			.stream()
			.map(PantryMapper::toEntity)
			.collect(Collectors.toList());
	}
}
