package com.example.tasker.domain.user.exception;

import jdk.jshell.spi.ExecutionControl;

public class PhoneNumDuplicateException extends UserException {
    public PhoneNumDuplicateException() {
        super(UserExceptionList.PHONE_NUM_DUPLICATE.getCODE(),
                UserExceptionList.PHONE_NUM_DUPLICATE.getHttpStatus(),
                UserExceptionList.PHONE_NUM_DUPLICATE.getMESSAGE()
        );
    }
}
