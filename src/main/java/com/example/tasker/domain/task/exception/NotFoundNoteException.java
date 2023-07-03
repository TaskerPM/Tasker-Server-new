package com.example.tasker.domain.task.exception;

public class NotFoundNoteException extends TaskException {
    public NotFoundNoteException() {
        super(TaskExceptionList.NOT_FOUND_NOTE.getCODE(),
                TaskExceptionList.NOT_FOUND_NOTE.getHttpStatus(),
                TaskExceptionList.NOT_FOUND_NOTE.getMESSAGE()
        );
    }
}
