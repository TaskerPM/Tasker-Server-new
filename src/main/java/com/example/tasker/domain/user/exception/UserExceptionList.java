package com.example.tasker.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionList {

    DENIED_ACCESS("U0001", HttpStatus.FORBIDDEN, "잘못된 접근입니다."),
    NOT_CERTIFIED("U0002", HttpStatus.BAD_REQUEST, "인증 코드가 일치하지 않습니다."),
    PHONE_NUM_DUPLICATE("U0003", HttpStatus.CONFLICT, "해당 전화번호는 이미 존재하는 전화번호입니다."),
    NOT_FOUND_USER("U0004", HttpStatus.BAD_REQUEST, "해당 유저를 찾을 수 없습니다.");

    private final String CODE;
    private final HttpStatus httpStatus;
    private final String MESSAGE;

}
