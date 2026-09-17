package com.tcc.faltaoque.dto.response;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public record TokenResponse(
	String token,
	String subject,
	String issuer,
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-03:00")
	Date issuedAt,
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-03:00")
	Date expiration,
	UserResponse user) {

}
