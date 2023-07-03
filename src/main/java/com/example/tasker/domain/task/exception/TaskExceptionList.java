package com.example.tasker.domain.task.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TaskExceptionList {

    NOT_FOUND_TASK("T0001", HttpStatus.BAD_REQUEST, "해당 테스크를 찾을 수 없습니다.");

    private final String CODE;
    private final HttpStatus httpStatus;
    private final String MESSAGE;

}
