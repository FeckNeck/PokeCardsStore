package com.feckneck.pokeCardsserver.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request.")
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super();
    }
    
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BadRequestException(String message) {
        super(message);
    }
    
    public BadRequestException(Throwable cause) {
        super(cause);
    }

}
