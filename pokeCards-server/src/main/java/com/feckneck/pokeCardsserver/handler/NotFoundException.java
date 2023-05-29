package com.feckneck.pokeCardsserver.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found.")
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }
    
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public NotFoundException(String message) {
        super(message);
    }
    
    public NotFoundException(Throwable cause) {
        super(cause);
    }

}
