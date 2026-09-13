package com.tcc.faltaoque.service;

import java.util.Date;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;

@Service
public class TokenService {

	@Value("${security.token_secret_key}")
	private String SECRET_KEY;

	SecretKey getKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
	}

	public String generateToken(String email) {
		return Jwts
			.builder()
			.subject(email)
			.issuer("faltaoque.API")
			.issuedAt(new Date())
			.signWith(getKey(), Jwts.SIG.HS256)
			.compact();
	}

	public String validateToken(String token) {
		return Jwts
			.parser()
			.verifyWith(getKey())
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.getSubject();
	}

}
