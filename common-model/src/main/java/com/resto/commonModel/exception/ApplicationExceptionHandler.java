package com.resto.commonModel.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AuthorizationNotFoundException.class)
	public final ResponseEntity<Object> handleAuthNotFoundExceptions(Exception ex, WebRequest request)
			throws Exception {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(),
				"The request was sent with no Authorization header");
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IncorrectAuthorizationException.class)
	public final ResponseEntity<Object> handleIncorrectAuthExceptions(Exception ex, WebRequest request)
			throws Exception {
		ExceptionResponse response = new ExceptionResponse(new Date(), "Access Denied", ex.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
		for (ConstraintViolation<?> contraints : constraintViolations) {
			errors.add(contraints.getMessage());
		}
		ExceptionResponse response = new ExceptionResponse(new Date(), "Invalid input", "Validation of fields failed", errors);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleUniqueError(DataIntegrityViolationException ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), "Data already exists", "Please try to post different data");
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Object> handleUserExistsError(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), "Bad Request", ex.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);
	}	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);
		System.out.println("BODY:::::" + body);
		return new ResponseEntity<>(body, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), "The Request Body is empty",
				"Please provide valid data in the body.");
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

}
