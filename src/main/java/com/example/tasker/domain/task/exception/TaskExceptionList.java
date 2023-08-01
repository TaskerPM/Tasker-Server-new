package com.example.tasker.domain.task.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TaskExceptionList {

    NOT_FOUND_TASK("T0001", HttpStatus.BAD_REQUEST, "해당 테스크를 찾을 수 없습니다."),
    TIME_ERR0R_1("T0002", HttpStatus.BAD_REQUEST, "다른 테스크와 시간이 겹칩니다. 시간을 다시 설정해주세요."),
    TIME_ERROR_2("T0003", HttpStatus.BAD_REQUEST, "시작 시간과 종료시간이 같습니다. 시간을 다시 설정해주세요."),
    TIME_ERROR_3("T0004", HttpStatus.BAD_REQUEST, "시작 시간보다 종료시간이 이릅니다. 시간을 다시 설정해주세요."),
    TIME_ERROR_4("T0005", HttpStatus.BAD_REQUEST, "종료 시간을 입력해주세요."),
    NOT_FOUND_NOTE("T0006", HttpStatus.BAD_REQUEST, "존재하지 않는 노트입니다."),
    NO_MODIFIED_TASK("T0007", HttpStatus.BAD_REQUEST, "변경사항이 없습니다.");

    private final String CODE;
    private final HttpStatus httpStatus;
    private final String MESSAGE;

}
