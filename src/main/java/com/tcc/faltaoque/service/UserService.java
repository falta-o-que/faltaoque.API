package com.tcc.faltaoque.service;

import com.tcc.faltaoque.entity.User;
import com.tcc.faltaoque.entity.Color;
import com.tcc.faltaoque.repository.UserRepository;
import com.tcc.faltaoque.repository.ColorRepository;
import com.tcc.faltaoque.mapper.UserMapper;
import com.tcc.faltaoque.mapper.PantryMapper;
import com.tcc.faltaoque.dto.request.UserRequest;
import com.tcc.faltaoque.dto.response.UserResponse;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.exception.UserNotFoundException;
import com.tcc.faltaoque.exception.ColorNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final ColorRepository colorRepository;

	public List<UserResponse> listAllUsers() {
		return userRepository.findAll()
			.stream()
			.map(user -> UserMapper.toResponse(user, user.getAvatar()))
			.collect(Collectors.toList());
	}

	public UserResponse findUserById(String id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));
		return UserMapper.toResponse(user, user.getAvatar());
	}

	public List<PantryResponse> listAllPantriesByUser(String id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));

		return user.getPantries()
			.stream()
			.map(pantry -> PantryMapper.toResponse(
				pantry, pantry.getColor(), pantry.getShareInvite()))
			.collect(Collectors.toList());
	}

	public UserResponse updateUser(String id, UserRequest userRequest) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));
		Color color = colorRepository.findById(userRequest.avatar())
			.orElseThrow(() -> new ColorNotFoundException(userRequest.avatar()));

		user.setName(userRequest.name());
		user.setEmail(userRequest.email());
		user.setPassword(userRequest.password());
		user.setAvatar(color);

		userRepository.save(user);
		return UserMapper.toResponse(user, user.getAvatar());
	}

	public void deleteUser(String id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));

		userRepository.deleteById(id);
	}

}
