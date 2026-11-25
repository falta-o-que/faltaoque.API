package com.tcc.faltaoque.service;

import com.tcc.faltaoque.entity.User;
import com.tcc.faltaoque.entity.Color;
import com.tcc.faltaoque.repository.UserRepository;
import com.tcc.faltaoque.repository.ColorRepository;
import com.tcc.faltaoque.mapper.UserMapper;
import com.tcc.faltaoque.dto.request.UserRequest;
import com.tcc.faltaoque.dto.request.UserLoginRequest;
import com.tcc.faltaoque.dto.response.UserResponse;
import com.tcc.faltaoque.dto.response.TokenResponse;
import com.tcc.faltaoque.exception.ColorNotFoundException;
import com.tcc.faltaoque.exception.ExistingEmailException;
import com.tcc.faltaoque.exception.InvalidCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final ColorRepository colorRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	public UserResponse createUser(UserRequest userRequest) {
		if (userRepository.existsByEmail(userRequest.email())) {
			throw new ExistingEmailException(userRequest.email());
		}

		Color color = colorRepository.findById(userRequest.avatar())
			.orElseThrow(() -> new ColorNotFoundException(userRequest.avatar()));
		User user = UserMapper.toEntity(userRequest, color);

		user.setPassword(passwordEncoder.encode(userRequest.password()));
		userRepository.save(user);
		return UserMapper.toResponse(user, user.getAvatar());
	}

	public TokenResponse login(UserLoginRequest userLoginRequest) {
		User user = userRepository.findByEmail(userLoginRequest.email())
			.orElseThrow(InvalidCredentialsException::new);

		if (!passwordEncoder.matches(userLoginRequest.password(), user.getPassword())) {
			throw new InvalidCredentialsException();
		}

		if (!user.getIsActive()) {
			throw new InvalidCredentialsException();
		}

		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				userLoginRequest.email(),
				userLoginRequest.password()));

		String token = tokenService.generateToken(userLoginRequest.email());
		Claims tokenClaims = tokenService.getTokenClaims(token);

		return new TokenResponse(
			token,
			tokenClaims.getSubject(),
			tokenClaims.getIssuer(),
			tokenClaims.getIssuedAt(),
			tokenClaims.getExpiration(),
			UserMapper.toResponse(user, user.getAvatar()));
	}

}
