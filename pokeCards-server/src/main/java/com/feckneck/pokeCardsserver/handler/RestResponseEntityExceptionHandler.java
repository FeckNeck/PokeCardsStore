package com.feckneck.pokeCardsserver.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller responsible for exception handling.
 */
@ControllerAdvice
@EnableWebMvc
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class, BadRequestException.class})
    protected ResponseEntity<Object> handleError(RuntimeException ex, WebRequest request) {
        String message = ex.getMessage();
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof NotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NotFoundException nfe = (NotFoundException) ex;
            return handleExceptionInternal(nfe, message, headers, status, request);

        } else if (ex instanceof BadRequestException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            BadRequestException bre = (BadRequestException) ex;
            return handleExceptionInternal(bre, message, headers, status, request);

        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, message, headers, status, request);
        }
    }
}
