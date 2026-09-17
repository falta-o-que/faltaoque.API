package com.tcc.faltaoque.exception;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.context.request.WebRequest;
import org.jspecify.annotations.Nullable;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private RestErrorResponse userNotFoundHandler(UserNotFoundException exception) {
		return RestErrorResponse.builder()
			.timestamp(LocalDateTime.now())
			.status(HttpStatus.NOT_FOUND.value())
			.error(HttpStatus.NOT_FOUND.name())
			.message(exception.getMessage())
			.build();
	}

	@Override
	protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(
	MethodArgumentNotValidException ex, HttpHeaders headers,
	HttpStatusCode status, WebRequest request) {

		List<String> errorList = ex.getBindingResult().getFieldErrors()
			.stream()
			.map(error -> error.getField() + " : " + error.getDefaultMessage())
			.toList();

		RestErrorResponse response = RestErrorResponse.builder()
			.timestamp(LocalDateTime.now())
			.status(HttpStatus.BAD_REQUEST.value())
			.error(HttpStatus.BAD_REQUEST.name())
			.validationErrors(errorList)
			.build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
