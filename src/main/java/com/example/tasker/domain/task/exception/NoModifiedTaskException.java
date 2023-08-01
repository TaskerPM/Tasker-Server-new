package com.example.tasker.domain.task.exception;

public class NoModifiedTaskException extends TaskException {
    public NoModifiedTaskException() {
        super(TaskExceptionList.NO_MODIFIED_TASK.getCODE(),
                TaskExceptionList.NO_MODIFIED_TASK.getHttpStatus(),
                TaskExceptionList.NO_MODIFIED_TASK.getMESSAGE()
        );
    }
}
