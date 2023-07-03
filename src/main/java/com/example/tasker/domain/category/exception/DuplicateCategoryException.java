package com.example.tasker.domain.category.exception;

import com.example.tasker.domain.report.exception.ReportException;
import com.example.tasker.domain.report.exception.ReportExceptionList;

public class DuplicateCategoryException extends ReportException {
    public DuplicateCategoryException() {
        super(CategoryExceptionList.DUPLICATE_CATEGORY.getCODE(),
                CategoryExceptionList.DUPLICATE_CATEGORY.getHttpStatus(),
                CategoryExceptionList.DUPLICATE_CATEGORY.getMESSAGE()
        );
    }
}
