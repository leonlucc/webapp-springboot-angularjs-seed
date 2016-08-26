package com.leonlu.code.sample.webapp.ws.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)  
    public void handleException(Exception e, HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}  
}
