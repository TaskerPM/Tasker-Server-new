package com.example.tasker.domain.report.exception;

import com.example.tasker.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class ReportException extends ApplicationException {

    protected ReportException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
