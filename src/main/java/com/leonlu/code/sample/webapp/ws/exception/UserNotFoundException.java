package com.leonlu.code.sample.webapp.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(Long userId) {
		super("could not find user with id '" + userId + "'.");
	}
	
	public UserNotFoundException(String userName) {
		super("could not find user named '" + userName + "'.");
	}
}
