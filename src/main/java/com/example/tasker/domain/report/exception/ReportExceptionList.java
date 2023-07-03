package com.example.tasker.domain.report.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReportExceptionList {

    NOT_FOUND_REPORT("R0001", HttpStatus.BAD_REQUEST, "해당 신고정보를 찾을 수 없습니다."),
    DUPLICATE_REPORT("R0002", HttpStatus.BAD_REQUEST, "이미 신고했습니다.");

    private final String CODE;
    private final HttpStatus httpStatus;
    private final String MESSAGE;

}
