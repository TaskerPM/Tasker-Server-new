package com.example.tasker.domain.task.exception;

public class TimeError3Exception extends TaskException {
    public TimeError3Exception() {
        super(TaskExceptionList.TIME_ERROR_3.getCODE(),
                TaskExceptionList.TIME_ERROR_3.getHttpStatus(),
                TaskExceptionList.TIME_ERROR_3.getMESSAGE()
        );
    }
}
