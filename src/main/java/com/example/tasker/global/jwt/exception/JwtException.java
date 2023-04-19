package com.example.tasker.global.jwt.exception;

import com.example.tasker.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class JwtException extends ApplicationException {

    protected JwtException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
