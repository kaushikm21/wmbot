package com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EngineerNotFoundException extends RuntimeException{
    public EngineerNotFoundException(String message) {
        super(message);
    }
}
