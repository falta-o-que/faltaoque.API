package com.tcc.faltaoque.exception;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RestErrorResponse (
	LocalDateTime timestamp,
	int status,
	String error,
	String message,
	List<String> validationErrors) {
}
