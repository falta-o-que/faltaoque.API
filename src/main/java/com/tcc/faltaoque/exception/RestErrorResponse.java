package com.tcc.faltaoque.exception;

import java.time.LocalDateTime;

public record RestErrorResponse (
	LocalDateTime timestamp,
	int status,
	String error,
	String message) {
}
