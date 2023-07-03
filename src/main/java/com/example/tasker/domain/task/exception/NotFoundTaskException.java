package com.example.tasker.domain.task.exception;

public class NotFoundTaskException extends TaskException {
    public NotFoundTaskException() {
        super(TaskExceptionList.NOT_FOUND_TASK.getCODE(),
                TaskExceptionList.NOT_FOUND_TASK.getHttpStatus(),
                TaskExceptionList.NOT_FOUND_TASK.getMESSAGE()
        );
    }
}
