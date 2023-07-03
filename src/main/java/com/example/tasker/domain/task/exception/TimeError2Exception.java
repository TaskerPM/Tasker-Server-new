package com.example.tasker.domain.task.exception;

public class TimeError2Exception extends TaskException {
    public TimeError2Exception() {
        super(TaskExceptionList.TIME_ERROR_2.getCODE(),
                TaskExceptionList.TIME_ERROR_2.getHttpStatus(),
                TaskExceptionList.TIME_ERROR_2.getMESSAGE()
        );
    }
}
