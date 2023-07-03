package com.example.tasker.domain.task.exception;

public class TimeError4Exception extends TaskException {
    public TimeError4Exception() {
        super(TaskExceptionList.TIME_ERROR_4.getCODE(),
                TaskExceptionList.TIME_ERROR_4.getHttpStatus(),
                TaskExceptionList.TIME_ERROR_4.getMESSAGE()
        );
    }
}
