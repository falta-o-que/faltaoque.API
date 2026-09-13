package com.tcc.faltaoque.service;

import com.tcc.faltaoque.entity.User;
import com.tcc.faltaoque.repository.UserRepository;
import com.tcc.faltaoque.mapper.UserMapper;
import com.tcc.faltaoque.dto.request.UserRequest;
import com.tcc.faltaoque.dto.request.UserLoginRequest;
import com.tcc.faltaoque.dto.response.UserResponse;
import com.tcc.faltaoque.dto.response.TokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	public UserResponse createUser(UserRequest userRequest) {
		User user = UserMapper.toRequest(userRequest);
		user.setPassword(passwordEncoder.encode(userRequest.password()));
		userRepository.save(user);
		return UserMapper.toEntity(user);
	}

	public TokenResponse login(UserLoginRequest userLoginRequest) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				userLoginRequest.email(),
				userLoginRequest.password()));

		String token = tokenService.generateToken(userLoginRequest.email());
		Claims tokenClaims = tokenService.getTokenClaims(token);

		User user = userRepository.findByEmail(userLoginRequest.email())
			.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

		return new TokenResponse(
			token,
			tokenClaims.getSubject(),
			tokenClaims.getIssuer(),
			tokenClaims.getIssuedAt(),
			tokenClaims.getExpiration(),
			UserMapper.toEntity(user));
	}

}
