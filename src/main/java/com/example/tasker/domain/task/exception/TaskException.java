package com.example.tasker.domain.task.exception;

import com.example.tasker.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class TaskException extends ApplicationException {

    protected TaskException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
