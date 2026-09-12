package com.tcc.faltaoque.exception;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private RestErrorResponse userNotFoundHandler(UserNotFoundException exception) {
		return new RestErrorResponse(
			LocalDateTime.now(),
			HttpStatus.NOT_FOUND.value(),
			HttpStatus.NOT_FOUND.name(),
			exception.getMessage());
	}
}
