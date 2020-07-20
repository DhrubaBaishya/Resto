package com.resto.commonModel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IncorrectAuthorizationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 791892342067935914L;

	public IncorrectAuthorizationException(String message) {
		super(message);
	}

}
