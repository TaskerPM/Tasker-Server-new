package com.example.tasker.domain.category.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CategoryExceptionList {

    NOT_FOUND_CATEGORY("C0001", HttpStatus.BAD_REQUEST, "해당 카테고리를 찾을 수 없습니다."),
    DUPLICATE_CATEGORY("C0002", HttpStatus.BAD_REQUEST, "이미 존재하는 카테고리입니다."),
    NO_MODIFIED_CATEGORY("C0003", HttpStatus.BAD_REQUEST, "변경사항이 없습니다."),
    NO_EXISTENT_COLOR("C0004", HttpStatus.BAD_REQUEST, "존재하지 않는 색깔입니다.");

    private final String CODE;
    private final HttpStatus httpStatus;
    private final String MESSAGE;

}
