package com.example.tasker.domain.category.exception;

import com.example.tasker.domain.report.exception.ReportException;
import com.example.tasker.domain.report.exception.ReportExceptionList;

public class NotFoundCategoryException extends ReportException {
    public NotFoundCategoryException() {
        super(CategoryExceptionList.NOT_FOUND_CATEGORY.getCODE(),
                CategoryExceptionList.NOT_FOUND_CATEGORY.getHttpStatus(),
                CategoryExceptionList.NOT_FOUND_CATEGORY.getMESSAGE()
        );
    }
}
