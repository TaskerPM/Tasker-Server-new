package com.example.tasker.domain.category.exception;

import com.example.tasker.domain.report.exception.ReportException;

public class NonExistentColorException extends ReportException {
    public NonExistentColorException() {
        super(CategoryExceptionList.NO_EXISTENT_COLOR.getCODE(),
                CategoryExceptionList.NO_EXISTENT_COLOR.getHttpStatus(),
                CategoryExceptionList.NO_EXISTENT_COLOR.getMESSAGE()
        );
    }
}