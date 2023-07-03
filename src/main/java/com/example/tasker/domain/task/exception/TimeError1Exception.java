package com.example.tasker.domain.task.exception;

public class TimeError1Exception extends TaskException {
    public TimeError1Exception() {
        super(TaskExceptionList.TIME_ERR0R_1.getCODE(),
                TaskExceptionList.TIME_ERR0R_1.getHttpStatus(),
                TaskExceptionList.TIME_ERR0R_1.getMESSAGE()
        );
    }
}
