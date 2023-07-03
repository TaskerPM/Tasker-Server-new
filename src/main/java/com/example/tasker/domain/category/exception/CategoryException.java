package com.example.tasker.domain.category.exception;

import com.example.tasker.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class CategoryException extends ApplicationException {

    protected CategoryException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
