package com.example.tasker.domain.report.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportType {
    R1("불법 정보"),
    R2("홍보, 영리 목적"),
    R3("욕설, 비방, 차별, 혐오"),
    R4("음란, 청소년 유해"),
    R5("개인정보 침해"),
    R6("명의 도용"),
    R7("도배, 스팸");

    private String report;

}
