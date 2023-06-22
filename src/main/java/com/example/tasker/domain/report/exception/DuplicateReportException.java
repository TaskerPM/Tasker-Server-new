package com.example.tasker.domain.report.exception;

public class DuplicateReportException extends ReportException {
    public DuplicateReportException() {
        super(ReportExceptionList.NOT_FOUND_REPORT.getCODE(),
                ReportExceptionList.NOT_FOUND_REPORT.getHttpStatus(),
                ReportExceptionList.NOT_FOUND_REPORT.getMESSAGE()
        );
    }
}
